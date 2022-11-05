package sh.hsp.labella.services.printing.converter.mono

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.*
import kotlin.math.ceil


class SimpleImageToMono : ImageToMono {
    override fun convert(image: BufferedImage): Mono {
        val realX: Int = (8 * ceil(image.width / 8.0)).toInt()
        val realY: Int = image.height

        val size = realX * realY

        val bitSet = BitSet(size)
        bitSet.set(size - 1)

        val blackImage = image.convertToBlackAndWhite()

        for (y in 0 until realY) {
            for (x in 0 until realX) {
                if (blackImage.getRGB(x, y) == -1) bitSet.set(y * realX + x.inverse())
            }
        }

        return Mono(bitSet, realX, realY)
    }

    private fun Int.inverse(): Int {
        return this / 8 * 8 + (7 - (this % 8))
    }

    private fun BufferedImage.convertToBlackAndWhite(): BufferedImage {
        val blackWhite = BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_BINARY)
        val g2d: Graphics2D = blackWhite.createGraphics()
        g2d.drawImage(this, 0, 0, null)
        g2d.dispose()

        return blackWhite
    }

}