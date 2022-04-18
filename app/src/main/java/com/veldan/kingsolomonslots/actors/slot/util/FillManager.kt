package com.veldan.kingsolomonslots.actors.slot.util

import com.veldan.kingsolomonslots.actors.slot.slot.Slot
import com.veldan.kingsolomonslots.actors.slot.slot.SlotController
import com.veldan.kingsolomonslots.actors.slot.util.combination.Combination
import com.veldan.kingsolomonslots.actors.slot.util.combination.CombinationMatrixEnum
import com.veldan.kingsolomonslots.utils.log
import com.veldan.kingsolomonslots.utils.probability

class FillManager(val slotList: List<Slot>) {

    var winFillResult: FillResult? = null
        private set



    private fun fillRandom(isUseWild: Boolean = true) {
        log("FILL_RANDOM")

        val combinationMatrixEnum: CombinationMatrixEnum = if (isUseWild && probability(20)) {
            log("FILL_RANDOM use WILD")
            Combination.RandomWithWild.values().random()
        } else Combination.Random.values().random()

        combinationMatrixEnum.logCombinationMatrixEnum()

        slotList.onEachIndexed { index, slot ->
            slot.slotItemWinList = combinationMatrixEnum.matrix.generateSlot(index)
        }
    }

    private fun fillWin() {
        log("FILL_WIN")

        val combinationMatrixEnum = listOf<CombinationMatrixEnum>(
            *Combination.WinMonochrome.values(),
            *Combination.WinColorfulDisjoint.values(),
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
        fillRandom(false)

        val randomSlotList = slotList.shuffled().take(2)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.wild)
        } } }
    }

    private fun fillSuper() {
        log("FILL_SUPER")
        fillRandom(false)

        val randomSlotList = slotList.shuffled().take(3)
        randomSlotList.onEach { slot -> with(slot) { slotItemWinList = slotItemWinList.toMutableList().apply {
            setInRandomRow(SlotItemContainer.wild)
        } } }
    }

    private fun MutableList<SlotItem>.setInRandomRow(slotItem: SlotItem) = apply {
        val randomRow = (0 until SlotController.SLOT_ITEM_VISIBLE_COUNT).random()
        set(randomRow, slotItem)
    }



    fun fill(fillStrategy: FillStrategy) {
        winFillResult = null

        when (fillStrategy) {
            FillStrategy.RANDOM -> fillRandom()
            FillStrategy.WIN    -> fillWin()
            FillStrategy.MINI   -> fillMini()
            FillStrategy.SUPER  -> fillSuper()
        }
    }



    private fun CombinationMatrixEnum.logCombinationMatrixEnum() {
        val combinationEnumName = when (this) {
            is Combination.Random              -> "Комбинация Рандом $name"
            is Combination.RandomWithWild      -> "Комбинация Рандом WILD $name"
            is Combination.WinMonochrome       -> "Комбинация Победа Одноцветная $name"
            is Combination.WinColorfulDisjoint -> "Комбинация Победа Разноцветная Непересекающаяся $name"
            else                               -> "Noname"
        }
        log(combinationEnumName)
    }

}