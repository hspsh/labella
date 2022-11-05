package sh.hsp.labella.services.printer

import org.junit.jupiter.api.Test
import javax.imageio.ImageIO

class PrinterServiceImplTest {

    @Test
    fun print() {
        val printService = PrinterServiceImpl()
        printService.print(ImageIO.read(this.javaClass.getResourceAsStream("img.png")))
    }

}