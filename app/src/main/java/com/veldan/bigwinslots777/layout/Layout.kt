package com.veldan.bigwinslots777.layout

object Layout {
    // ------------------------------------------------------------------------
    // Splash
    // ------------------------------------------------------------------------
    object Splash {
        val PROGRESS_X = 562f
        val PROGRESS_Y = 23f
        val PROGRESS_W = 275f
        val PROGRESS_H = 151f
    }

    // ------------------------------------------------------------------------
    // Game
    // ------------------------------------------------------------------------
    object Game {
        val BALANCE_PANEL_X = 940f
        val BALANCE_PANEL_Y = 559f
        val BALANCE_PANEL_W = 456f
        val BALANCE_PANEL_H = 105f

        val BALANCE_TEXT_X = 38f
        val BALANCE_TEXT_Y = 17f
        val BALANCE_TEXT_W = 379f
        val BALANCE_TEXT_H = 72f

        val BET_PANEL_X = 404f
        val BET_PANEL_Y = 11f
        val BET_PANEL_W = 349f
        val BET_PANEL_H = 122f

        val BET_TEXT_X = 40f
        val BET_TEXT_Y = 25f
        val BET_TEXT_W = 269f
        val BET_TEXT_H = 72f

        val BET_PLUS_X = 262f
        val BET_PLUS_Y = 17f
        val BET_PLUS_W = 110f
        val BET_PLUS_H = 110f

        val BET_MINUS_X = 785f
        val BET_MINUS_Y = 17f
        val BET_MINUS_W = 110f
        val BET_MINUS_H = 110f

        val MUSIC_X = 15f
        val MUSIC_Y = 17f
        val MUSIC_W = 110f
        val MUSIC_H = 110f

        val AUTO_SPIN_X = 1111f
        val AUTO_SPIN_Y = 84f
        val AUTO_SPIN_W = 115f
        val AUTO_SPIN_H = 123f

        val SPIN_X = 1051f
        val SPIN_Y = 266f
        val SPIN_W = 234f
        val SPIN_H = 234f

        val SPIN_TEXT_X = 24f
        val SPIN_TEXT_Y = 69f
        val SPIN_TEXT_W = 185f
        val SPIN_TEXT_H = 96f

        val SLOT_GROUP_X = 15f
        val SLOT_GROUP_Y = 145f

        val BALANCE_PANEL_SUPER_X = 940f
        val BALANCE_PANEL_SUPER_Y = 415f

        val AUTO_SPIN_SUPER_X = 983f
        val AUTO_SPIN_SUPER_Y = 145f

        val SPIN_SUPER_X = 1144f
        val SPIN_SUPER_Y = 89f

        val SUPER_ELEMENT_X = 960f
        val SUPER_ELEMENT_Y = 549f
    }

    // ------------------------------------------------------------------------
    // Slot
    // ------------------------------------------------------------------------
    object Slot {
        val W = 170f
        val H = 17480f

        val START_Y = 10f
        val END_Y   = -16965f

        val SLOT_ITEM_SPACE_VERTICAL = 20f
        val SLOT_ITEM_W = 170f
        val SLOT_ITEM_H = 155f
    }

    // ------------------------------------------------------------------------
    // Glow
    // ------------------------------------------------------------------------
    object Glow {
        val W = 281f
        val H = 605f

        val GLOW_ITEM_FIRST_Y = 305f
        val GLOW_ITEM_SPACE_VERTICAL = -80f
        val GLOW_ITEM_W = 281f
        val GLOW_ITEM_H = 255f
    }

    // ------------------------------------------------------------------------
    // SlotGroup
    // ------------------------------------------------------------------------
    object SlotGroup {
        val W = 920f
        val H = 540f

        val MASK_X = 8f
        val MASK_Y = 8f
        val MASK_W = 904f
        val MASK_H = 524f

        val SLOT_FIRST_X = 25f
        val SLOT_SPACE_HORIZONTAL = 58f

        val GLOW_FIRST_X = -23f
        val GLOW_Y = 10f
        val GLOW_SPACE_HORIZONTAL = -53f
    }

    // ------------------------------------------------------------------------
    // TutorialGroup
    // ------------------------------------------------------------------------
    object TutorialGroup {

        val SKIP_X = 1271f
        val SKIP_Y = 571f
        val SKIP_W = 99f
        val SKIP_H = 99f

        object Balance: TutorialItemLayout {
            override val FRAME_X = 469f
            override val FRAME_Y = 13f
            override val FRAME_W = 465f
            override val FRAME_H = 92f

            override val DIALOG_X = 339f
            override val DIALOG_Y = 119f
            override val DIALOG_W = 722f
            override val DIALOG_H = 463f

            override val TEXT_X = 425f
            override val TEXT_Y = 179f
            override val TEXT_W = 552f
            override val TEXT_H = 340f
        }

        object Bet: TutorialItemLayout {
            override val FRAME_X = 2f
            override val FRAME_Y = 299f
            override val FRAME_W = 304f
            override val FRAME_H = 102f

            override val DIALOG_X = 339f
            override val DIALOG_Y = 119f
            override val DIALOG_W = 722f
            override val DIALOG_H = 463f

            override val TEXT_X = 425f
            override val TEXT_Y = 179f
            override val TEXT_W = 552f
            override val TEXT_H = 340f
        }

        object Spin: TutorialItemLayout {
            override val FRAME_X = 1143f
            override val FRAME_Y = 251f
            override val FRAME_W = 199f
            override val FRAME_H = 199f

            override val DIALOG_X = 339f
            override val DIALOG_Y = 119f
            override val DIALOG_W = 722f
            override val DIALOG_H = 463f

            override val TEXT_X = 425f
            override val TEXT_Y = 179f
            override val TEXT_W = 552f
            override val TEXT_H = 340f
        }

        object PlusMinus: TutorialItemLayout {
            override val FRAME_X = 15f
            override val FRAME_Y = 135f
            override val FRAME_W = 279f
            override val FRAME_H = 128f

            override val DIALOG_X = 339f
            override val DIALOG_Y = 119f
            override val DIALOG_W = 722f
            override val DIALOG_H = 463f

            override val TEXT_X = 425f
            override val TEXT_Y = 179f
            override val TEXT_W = 552f
            override val TEXT_H = 340f
        }

        object SlotGroup: TutorialItemLayout {
            override val FRAME_X = 301f
            override val FRAME_Y = 111f
            override val FRAME_W = 790f
            override val FRAME_H = 475f

            override val DIALOG_X = 10f
            override val DIALOG_Y = 43f
            override val DIALOG_W = 287f
            override val DIALOG_H = 594f

            override val TEXT_X = 16f
            override val TEXT_Y = 143f
            override val TEXT_W = 275f
            override val TEXT_H = 379f
        }

        interface TutorialItemLayout{
            val FRAME_X: Float
            val FRAME_Y: Float
            val FRAME_W: Float
            val FRAME_H: Float

            val DIALOG_X: Float
            val DIALOG_Y: Float
            val DIALOG_W: Float
            val DIALOG_H: Float

            val TEXT_X: Float
            val TEXT_Y: Float
            val TEXT_W: Float
            val TEXT_H: Float
        }
    }

    // ------------------------------------------------------------------------
    // SuperGameGroup
    // ------------------------------------------------------------------------
    object SuperGameGroup {
        val ROULETTE_PANEL_X = 195f
        val ROULETTE_PANEL_Y = 38f
        val ROULETTE_PANEL_W = 624f
        val ROULETTE_PANEL_H = 624f

        val ROULETTE_X = 240f
        val ROULETTE_Y = 85f
        val ROULETTE_W = 532f
        val ROULETTE_H = 532f

        val INDICATOR_X = 483f
        val INDICATOR_Y = 580f
        val INDICATOR_W = 46f
        val INDICATOR_H = 40f

        val CENTER_X = 476f
        val CENTER_Y = 318f
        val CENTER_W = 63f
        val CENTER_H = 63f

        val SPIN_X = 992f
        val SPIN_Y = 233f
        val SPIN_W = 234f
        val SPIN_H = 234f

        val SPIN_TEXT_X = 24f
        val SPIN_TEXT_Y = 69f
        val SPIN_TEXT_W = 185f
        val SPIN_TEXT_H = 96f

        val ELEMENT_X = 901f
        val ELEMENT_Y = 549f

        val DIALOG_X = 240f
        val DIALOG_Y = 110f
        val DIALOG_W = 920f
        val DIALOG_H = 480f

        object Dialog {
            val TEXT_X = 12f
            val TEXT_W = 896f
            val TEXT_H = 117f

            val FAIL_Y = 217f
            val WIN_Y  = 313f

            val ELEMENT_X = 252f
            val ELEMENT_Y = 139f
        }
    }

    // ------------------------------------------------------------------------
    // SuperGameElementGroup
    // ------------------------------------------------------------------------
    object SuperGameElementGroup {
        val W = 416f
        val H = 123f

        val PANEL_FIRST_X = 0f
        val PANEL_SPACE_HORIZONTAL = 34f
        val PANEL_Y = 0f
        val PANEL_W = 116f
        val PANEL_H = 123f

        val LABEL_FIRST_X = 22f
        val LABEL_SPACE_HORIZONTAL = 77f
        val LABEL_Y = 33f
        val LABEL_W = 73f
        val LABEL_H = 73f

    }
}












