package sh.hsp.labella.model.ports

import java.awt.image.BufferedImage

interface ImageToLanguage {
    fun convert(image: BufferedImage): ByteArray
}