package com.veldan.bigwinslots777.actors.super_game

import com.veldan.bigwinslots777.advanced.group.AbstractAdvancedGroup

class SuperGameGroup: AbstractAdvancedGroup() {
    override val controller = SuperGameGroupController(this)


}