package sh.hsp.labella.services.printer.converter

import java.awt.image.BufferedImage

interface ImageToLanguage {
    fun convert(image: BufferedImage): ByteArray
}