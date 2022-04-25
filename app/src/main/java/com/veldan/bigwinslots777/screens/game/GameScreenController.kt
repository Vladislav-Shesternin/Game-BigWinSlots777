package com.veldan.bigwinslots777.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Disposable
import com.veldan.bigwinslots777.actors.slot.util.Bonus
import com.veldan.bigwinslots777.actors.slot.util.SpinResult
import com.veldan.bigwinslots777.manager.DataStoreManager
import com.veldan.bigwinslots777.utils.*
import com.veldan.bigwinslots777.utils.controller.ScreenController
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class GameScreenController(override val screen: GameScreen): ScreenController, Disposable {

    companion object {
        const val BET_STEP              = 50L
        const val BET_MIN               = 50L
        const val BET_MAX               = 1000L

        // seconds
        const val TIME_WAIT_AFTER_AUTOSPIN = 1f
        const val TIME_HIDE_GROUP          = 1f
        const val TIME_SHOW_GROUP          = 1f
    }

    private val coroutineMain = CoroutineScope(Dispatchers.Default)

    private val betFlow           = MutableStateFlow(BET_MIN)
    private val autoSpinStateFlow = MutableStateFlow(AutospinState.DEFAULT)

    private val onceStartAutoSpin = Once()

    var isPlayMusic = true



    override fun dispose() {
        cancelCoroutinesAll(coroutineMain)
    }



    private fun startAutospin() {
        coroutineMain.launch {
            autoSpinStateFlow.collect { state ->
                when (state) {
                    AutospinState.GO      -> {
                        with(screen) {
                            autoSpinButton.controller.press()
                            spinButton.controller.disable()
                            betPlusButton.controller.disable()
                            betMinusButton.controller.disable()

                            CoroutineScope(Dispatchers.Default).launch {
                                while (autoSpinStateFlow.value == AutospinState.GO) {
                                    if (checkBalance()) {
                                        spinAndSetResult()
                                        delay(TIME_WAIT_AFTER_AUTOSPIN.toDelay)
                                    }
                                    else autoSpinStateFlow.value = AutospinState.DEFAULT
                                }

                                autoSpinButton.controller.enable()
                                spinButton.controller.enable()
                                betPlusButton.controller.enable()
                                betMinusButton.controller.enable()
                                cancel()
                            }
                        }
                    }

                    AutospinState.DEFAULT -> screen.autoSpinButton.controller.disable()
                }
            }
        }
    }

    private suspend fun checkBalance() = CompletableDeferred<Boolean>().also { continuation ->
        DataStoreManager.Balance.update { balance ->
            if ((balance - betFlow.value) >= 0) {
                // Достаточно средств для запуска
                continuation.complete(true)
                balance - betFlow.value
            } else {
                // Недостаточно средств для запуска
                continuation.complete(false)
                balance
            }
        }
    }.await()

    private suspend fun SpinResult.calculateAndSetResult() {
        val slotItemPriceFactor: Float = winSlotItemSet?.map { it.priceFactor }?.sum() ?: 0f
        val sumPrice = (betFlow.value * slotItemPriceFactor).toLong()
        DataStoreManager.Balance.update { it + sumPrice }
    }

    private suspend fun spinAndSetResult() {
        screen.slotGroup.controller.spin().apply {
            when (bonus) {
                Bonus.MINI_GAME  -> {}//startMiniGame()
                Bonus.SUPER_GAME -> startSuperGame()
                else             -> {}
            }
            calculateAndSetResult()
        }
    }

    private suspend fun startSuperGame() = CompletableDeferred<Boolean>().also { continuation ->
        Gdx.app.postRunnable {
            with(screen) {
                addSuperGameGroup()
                coroutineMain.launch {
                    gameGroup.hideAnim(TIME_HIDE_GROUP)
                    superGameGroup.showAnim(TIME_SHOW_GROUP)
                }
            }
        }
    }.await()



    fun updateBalance() {
        coroutineMain.launch { DataStoreManager.Balance.collect { balance -> Gdx.app.postRunnable {
            screen.balanceTextLabel.controller.setText(balance.transformToBalanceFormat())
        } } }
    }

    fun updateBet() {
        coroutineMain.launch { with(betFlow) {
            emit(BET_MIN)
            collect { bet -> Gdx.app.postRunnable { screen.betTextLabel.controller.setText(bet.transformToBalanceFormat()) } }
        } }
    }

    fun betPlusHandler() {
        coroutineMain.launch { with(betFlow) {
            value = if ((value + BET_STEP) < BET_MAX) value + BET_STEP else BET_MAX
        } }
    }

    fun betMinusHandler() {
        coroutineMain.launch { with(betFlow) {
            value = if ((value - BET_STEP) > BET_MIN) value - BET_STEP else BET_MIN
        } }
    }

    fun spinHandler() {
        with(screen) {
            spinButton.controller.pressAndDisable(false)
            autoSpinButton.controller.disable()
            betPlusButton.controller.disable()
            betMinusButton.controller.disable()

            coroutineMain.launch {
                if (checkBalance()) spinAndSetResult()

                spinButton.controller.enable()
                autoSpinButton.controller.enable()
                betPlusButton.controller.enable()
                betMinusButton.controller.enable()
            }
        }
    }

    fun autoSpinHandler() {
        autoSpinStateFlow.apply {
            value = if (value == AutospinState.DEFAULT) AutospinState.GO else AutospinState.DEFAULT
        }
        onceStartAutoSpin.once { startAutospin() }
    }


    enum class AutospinState {
        DEFAULT, GO,
    }

}