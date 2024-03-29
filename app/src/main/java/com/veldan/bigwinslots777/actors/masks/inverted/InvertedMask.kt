package com.veldan.bigwinslots777.actors.masks.inverted

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup

class InvertedMask(
    val region: TextureRegion,
) : AbstractAdvancedGroup() {
    override val controller = InvertedMaskController(this)



    override fun draw(batch: Batch?, parentAlpha: Float) {
       controller.draw(batch, parentAlpha)
    }



    fun drawSuper(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

}