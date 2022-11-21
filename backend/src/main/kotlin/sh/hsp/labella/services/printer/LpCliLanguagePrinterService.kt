package sh.hsp.labella.services.printer

import org.slf4j.LoggerFactory
import sh.hsp.labella.model.LanguagePrinterService
import java.io.File

class LpCliLanguagePrinterService : LanguagePrinterService {
    val logger = LoggerFactory.getLogger(LpCliLanguagePrinterService::class.java)

    lateinit var printerName: String

    constructor(printerName: String) {
        this.printerName = printerName
    }

    override fun print(image: ByteArray) {
        val tmpFile = File.createTempFile("labella", ".epl2")

        try {
            tmpFile.writeBytes(image)

            val cmd = "lp -d ${printerName} -o raw ${tmpFile.absolutePath}"
            val process = Runtime.getRuntime().exec(cmd)

            val exit = process.waitFor()

            logger.info("CMD $cmd exit code $exit")

        } finally {
            tmpFile.delete()
        }
    }

    override fun cancelAll() {
        TODO("Not yet implemented")
    }
}