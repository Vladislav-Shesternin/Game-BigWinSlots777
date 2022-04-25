package com.veldan.bigwinslots777.manager.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.veldan.bigwinslots777.manager.assets.util.FontTTFData

object FontTTFManager {

    private const val pathNotoSans = "font/TTF/NotoSans.ttf"
    private const val pathRockwell = "font/TTF/Rockwell.ttf"
    private const val pathAmarante = "font/TTF/Amarante.ttf"

    private val resolverInternal = InternalFileHandleResolver()

    var loadListFont = mutableListOf<FontTTFData>()



    private fun AssetManager.setLoaderTTF() {
        setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolverInternal))
        setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolverInternal))
    }

    private fun getLoaderParameter(
        path: String,
        parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit = { }
    ) = FreetypeFontLoader.FreeTypeFontLoaderParameter().apply {
        fontFileName = path
        fontParameters.apply {
            minFilter = TextureFilter.Linear
            magFilter = TextureFilter.Linear
            incremental = true
            parameters()
        }
    }



    fun load(assetManager: AssetManager) {
        with(assetManager) {
            setLoaderTTF()
            loadListFont.onEach { load(it.name  + ".ttf", BitmapFont::class.java, it.parameters) }
        }
    }

    fun init(assetManager: AssetManager) {
        loadListFont.onEach { it.font = assetManager[it.name + ".ttf", BitmapFont::class.java] }
    }



    object RockwellFont : IFont {
        override val font_50 = FontTTFData("Rockwell_50", getLoaderParameter(pathRockwell) { size = 50 })

        val font_60 = FontTTFData("Rockwell_60", getLoaderParameter(pathRockwell) { size = 60 })

        override val values: List<FontTTFData>
            get() = super.values + font_60
    }

    object AmaranteFont: IFont {
        override val font_50 = FontTTFData("Amarante_50", getLoaderParameter(pathAmarante) { size = 50 })
    }

    object NotoSansFont: IFont {
        override val font_50 = FontTTFData("NotoSans_50", getLoaderParameter(pathNotoSans) { size = 50 })
    }



    interface IFont {
        val font_50: FontTTFData

        val values get() = listOf<FontTTFData>(font_50)
    }
}