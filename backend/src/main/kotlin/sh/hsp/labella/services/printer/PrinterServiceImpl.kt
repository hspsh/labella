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
import javax.print.attribute.standard.Copies

class PrinterServiceImpl : PrinterService {
    override fun print(image: BufferedImage) {
        val printServices = PrintServiceLookup.lookupPrintServices(null, null)
        val printService = printServices[2]
        val printJob = printService.createPrintJob()

        val printAttributes = HashPrintRequestAttributeSet()
        printAttributes.add(Copies(2))

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