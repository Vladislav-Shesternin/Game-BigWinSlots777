package com.veldan.bigwinslots777.utils.language

import android.content.res.Configuration
import com.veldan.bigwinslots777.main.game
import java.util.*

object Language {

    var locale: Locale = Locale.getDefault()



    fun getStringResource(
        resourceId: Int,
        locale: Locale = this.locale,
    ): String = with(game.activity) { Configuration(resources.configuration).run {
         setLocale(locale)
         createConfigurationContext(this).getString(resourceId)
    } }

}