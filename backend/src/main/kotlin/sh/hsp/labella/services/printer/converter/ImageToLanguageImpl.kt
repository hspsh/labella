package sh.hsp.labella.services.printer.converter

import sh.hsp.labella.model.ImageToLanguage
import sh.hsp.labella.services.printer.converter.mono.ImageToMono
import sh.hsp.labella.services.printer.converter.zebra.MonoToLanguage
import java.awt.image.BufferedImage

class ImageToLanguageImpl(val imageToMono: ImageToMono, val monoToLanguage: MonoToLanguage) : ImageToLanguage {
    override fun convert(image: BufferedImage): ByteArray {
        return image
            .let { imageToMono.convert(it) }
            .let { monoToLanguage.convert(it) }
    }
}