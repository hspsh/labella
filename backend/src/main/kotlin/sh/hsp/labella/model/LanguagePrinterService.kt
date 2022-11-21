package sh.hsp.labella.model

interface LanguagePrinterService {
    fun print(language: ByteArray): Unit
    fun cancelAll(): Unit
}