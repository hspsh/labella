package sh.hsp.labella.services.printer

import java.awt.image.BufferedImage

interface PrinterService {
    fun print(image: BufferedImage): Unit
    fun cancelAll(): Unit
}