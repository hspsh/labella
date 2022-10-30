package sh.hsp.labella.services.printer

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import javax.print.DocFlavor
import javax.print.PrintServiceLookup
import javax.print.SimpleDoc
import javax.print.attribute.HashDocAttributeSet
import javax.print.attribute.HashPrintRequestAttributeSet

class PrinterServiceImpl : PrinterService {
    override fun print(image: BufferedImage) {
        val printService = PrintServiceLookup.lookupPrintServices(null, null)[3]
        val printJob = printService.createPrintJob()

        val printAttributes = HashPrintRequestAttributeSet()

        val docAttributes = HashDocAttributeSet()


        val os = ByteArrayOutputStream()
        ImageIO.write(image, "png", os)
        val inputStream = ByteArrayInputStream(os.toByteArray())

        val doc = SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.PNG, docAttributes);

        printJob.print(doc, printAttributes)
    }

    override fun cancelAll() {
        TODO("Not yet implemented")
    }
}