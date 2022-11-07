package sh.hsp.labella.services.printer

interface LanguagePrinterService {
    fun print(language: ByteArray): Unit
    fun cancelAll(): Unit
}