package sh.hsp.labella.services.printing.converter

import sh.hsp.labella.services.printing.converter.mono.ImageToMono
import sh.hsp.labella.services.printing.converter.zebra.MonoToLanguage
import java.awt.image.BufferedImage

class ImageToLanguageImpl(val imageToMono: ImageToMono, val monoToLanguage: MonoToLanguage) : ImageToLanguage {
    override fun convert(image: BufferedImage): ByteArray {
        return image
            .let { imageToMono.convert(it) }
            .let { monoToLanguage.convert(it) }
    }
}