package com.veldan.bigwinslots777.actors.roulette.superGameRoulette

import com.veldan.bigwinslots777.actors.roulette.RouletteGroup
import com.veldan.bigwinslots777.manager.assets.SpriteManager

private const val SEGMENT = 45

private val rouletteItemList = listOf<SuperGameRouletteItem>(
    SuperGameRouletteItem(segment = (SEGMENT * 0f) + 0.0001f to (SEGMENT * 1f), number = 3 ),
    SuperGameRouletteItem(segment = (SEGMENT * 1f) + 0.0001f to (SEGMENT * 2f), number = 2 ),
    SuperGameRouletteItem(segment = (SEGMENT * 2f) + 0.0001f to (SEGMENT * 3f), number = 1 ),
    SuperGameRouletteItem(segment = (SEGMENT * 3f) + 0.0001f to (SEGMENT * 4f), number = 0 ),
    SuperGameRouletteItem(segment = (SEGMENT * 4f) + 0.0001f to (SEGMENT * 5f), number = 3 ),
    SuperGameRouletteItem(segment = (SEGMENT * 5f) + 0.0001f to (SEGMENT * 6f), number = 2 ),
    SuperGameRouletteItem(segment = (SEGMENT * 6f) + 0.0001f to (SEGMENT * 7f), number = 1 ),
    SuperGameRouletteItem(segment = (SEGMENT * 7f) + 0.0001f to (SEGMENT * 8f), number = 0 ),
)

class RouletteWild: RouletteGroup<SuperGameRouletteItem>(
    rouletteRegion = SpriteManager.SuperGameRegion.ROULETTE_WILD.region,
    items          = rouletteItemList,
)