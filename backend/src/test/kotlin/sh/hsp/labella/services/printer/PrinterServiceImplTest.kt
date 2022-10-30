package sh.hsp.labella.services.printer

import org.junit.jupiter.api.Test
import javax.imageio.ImageIO
import javax.print.PrintServiceLookup

class PrinterServiceImplTest {

    @Test
    fun print() {
        val printService = PrinterServiceImpl()
        printService.print(ImageIO.read(this.javaClass.getResourceAsStream("/img.png")))
    }

}