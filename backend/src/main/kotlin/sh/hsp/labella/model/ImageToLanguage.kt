package sh.hsp.labella.model

import java.awt.image.BufferedImage

interface ImageToLanguage {
    fun convert(image: BufferedImage): ByteArray
}