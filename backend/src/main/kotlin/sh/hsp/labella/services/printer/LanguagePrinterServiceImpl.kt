package sh.hsp.labella.services.printer

import javax.print.DocFlavor
import javax.print.PrintService
import javax.print.PrintServiceLookup
import javax.print.SimpleDoc
import javax.print.attribute.HashAttributeSet
import javax.print.attribute.HashDocAttributeSet
import javax.print.attribute.HashPrintRequestAttributeSet
import javax.print.attribute.standard.PrinterName

class LanguagePrinterServiceImpl : LanguagePrinterService {
    lateinit var printService: PrintService

    constructor(printerName: String) {
        val attributes = HashAttributeSet()
        attributes.add(PrinterName(printerName, null))

        printService =
            PrintServiceLookup.lookupPrintServices(null, attributes).firstOrNull()
                ?: throw RuntimeException("Printer of name '$printerName' not found")
    }

    override fun print(image: ByteArray) {
        val printJob = printService.createPrintJob()

        val printAttributes = HashPrintRequestAttributeSet()
        val docAttributes = HashDocAttributeSet()

        val doc = SimpleDoc(image, DocFlavor.BYTE_ARRAY.AUTOSENSE, docAttributes);

        printJob.print(doc, printAttributes)
    }

    override fun cancelAll() {
        TODO("Not yet implemented")
    }
}