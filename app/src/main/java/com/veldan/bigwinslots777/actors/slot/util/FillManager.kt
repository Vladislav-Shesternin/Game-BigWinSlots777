package com.veldan.bigwinslots777.actors.slot.util

import com.veldan.bigwinslots777.actors.slot.slot.Slot
import com.veldan.bigwinslots777.actors.slot.slot.SlotController
import com.veldan.bigwinslots777.actors.slot.util.combination._3x4.Combination
import com.veldan.bigwinslots777.actors.slot.util.combination._3x4.CombinationMatrixEnum
import com.veldan.bigwinslots777.utils.log
import com.veldan.bigwinslots777.utils.probability

class FillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillMix(isUseWild: Boolean = true) {
        log("FILL_MIX")

        val combinationMatrixEnum: CombinationMatrixEnum = if (isUseWild && probability(20)) {
            log("FILL_RANDOM use WILD")
            Combination.MixWithWild.values().random()
        } else Combination.Mix.values().random()

        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot -> slot.slotItemWinList = combinationMatrix.generateSlot(index) }
    }

    private fun fillWin() {
        log("FILL_WIN")

        val combinationMatrixEnum = listOf<CombinationMatrixEnum>(
            *Combination.WinMonochrome.values(),
            *Combination.WinMonochromeWithWild.values(),
            *Combination.WinColorful.values(),
            *Combination.WinColorfulWithWild.values(),
        ).random()
        combinationMatrixEnum.logCombinationMatrixEnum()
        val combinationMatrix = combinationMatrixEnum.matrix.init()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrix.generateSlot(index)
        }

        log("""
            scheme = ${combinationMatrix.scheme}
            slotIndex = ${combinationMatrix.intersectionList?.map { it.slotIndex }}
            rowIndex = ${combinationMatrix.intersectionList?.map { it.rowIndex }}
        """.trimIndent())

        winFillResult = with(combinationMatrix) {
            if (winSlotItemList != null) FillResult(winSlotItemList!!, intersectionList!!)
            else null
        }
    }

    private fun fillMini() {
        log("FILL_MINI")
        fillMix(false)

        val randomSlotList = slotList.shuffled().take(2)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.scatter)
        } } }
    }

    private fun fillSuper() {
        log("FILL_SUPER")
        fillMix(false)

        val randomSlotList = slotList.shuffled().take(3)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.scatter)
        } } }
    }

    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) = apply {
        val randomRow = (0 until SlotController.SLOT_ITEM_VISIBLE_COUNT).random()
        set(randomRow, slotItem)
    }



    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            is FillStrategy.MIX   -> fillMix()
            is FillStrategy.WIN   -> fillWin()
            is FillStrategy.MINI  -> fillMini()
            is FillStrategy.SUPER -> fillSuper()
        }
    }



    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is Combination.Mix                   -> "Mix $name"
            is Combination.MixWithWild           -> "Mix WILD $name"
            is Combination.WinMonochrome         -> "Win MONOCHROME $name"
            is Combination.WinMonochromeWithWild -> "Win MONOCHROME WILD $name"
            is Combination.WinColorful           -> "Win COLORFUL $name"
            is Combination.WinColorfulWithWild   -> "Win COLORFUL WILD $name"
            else                                 -> "Noname"
        }
        log(combinationEnumName)
    }

}