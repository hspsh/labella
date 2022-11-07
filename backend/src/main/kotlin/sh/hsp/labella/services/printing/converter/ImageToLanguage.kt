package sh.hsp.labella.services.printing.converter

import java.awt.image.BufferedImage

interface ImageToLanguage {
    fun convert(image: BufferedImage): ByteArray
}