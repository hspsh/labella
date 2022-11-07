package sh.hsp.labella.services.printer

interface PrinterService {
    fun print(language: ByteArray): Unit
    fun cancelAll(): Unit
}