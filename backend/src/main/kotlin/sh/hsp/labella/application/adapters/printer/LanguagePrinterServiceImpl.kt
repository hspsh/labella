package sh.hsp.labella.application.adapters.printer

import sh.hsp.labella.application.adapters.printer.converter.zebra.bytes
import sh.hsp.labella.model.ports.LanguagePrinterService
import javax.print.DocFlavor
import javax.print.PrintService
import javax.print.PrintServiceLookup
import javax.print.SimpleDoc
import javax.print.attribute.HashAttributeSet
import javax.print.attribute.HashDocAttributeSet
import javax.print.attribute.HashPrintRequestAttributeSet
import javax.print.attribute.standard.PrinterName

class LanguagePrinterServiceImpl(printerName: String) : LanguagePrinterService {
    var printService: PrintService

    init {
        val attributes = HashAttributeSet()
        attributes.add(PrinterName(printerName, null))
        printService =
            PrintServiceLookup.lookupPrintServices(null, attributes).firstOrNull()
                ?: throw RuntimeException("Printer of name '$printerName' not found")
        factoryReset()
    }

    override fun print(code: ByteArray) {
        val printJob = printService.createPrintJob()

        val printAttributes = HashPrintRequestAttributeSet()
        val docAttributes = HashDocAttributeSet()

        val doc = SimpleDoc(code, DocFlavor.BYTE_ARRAY.AUTOSENSE, docAttributes);

        printJob.print(doc, printAttributes)
    }

    private fun factoryReset() {
        print("^default\n".bytes().toByteArray())
    }

    override fun cancelAll() {
        TODO("Not yet implemented")
    }
}