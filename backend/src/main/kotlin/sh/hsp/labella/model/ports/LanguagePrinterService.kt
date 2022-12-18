package sh.hsp.labella.model.ports

interface LanguagePrinterService {
    fun print(language: ByteArray): Unit
    fun cancelAll(): Unit
}