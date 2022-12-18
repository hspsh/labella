package sh.hsp.labella.application.adapters.printer.converter

import sh.hsp.labella.application.adapters.printer.converter.mono.ImageToMono
import sh.hsp.labella.application.adapters.printer.converter.zebra.MonoToLanguage
import sh.hsp.labella.model.ports.ImageToLanguage
import java.awt.image.BufferedImage

class ImageToLanguageImpl(val imageToMono: ImageToMono, val monoToLanguage: MonoToLanguage) : ImageToLanguage {
    override fun convert(image: BufferedImage): ByteArray {
        return image
            .let { imageToMono.convert(it) }
            .let { monoToLanguage.convert(it) }
    }
}