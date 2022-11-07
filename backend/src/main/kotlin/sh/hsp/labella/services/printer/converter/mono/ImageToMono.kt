package sh.hsp.labella.services.printer.converter.mono

import java.awt.image.BufferedImage
import java.util.*

interface ImageToMono {
    fun convert(image: BufferedImage): Mono
}

data class Mono(val bitSet: BitSet, val width: Int, val height: Int)