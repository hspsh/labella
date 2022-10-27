package sh.hsp.labella.services.printing

interface PrintingService {
    fun printMd(contents: String, fields: Map<String, String>)
    fun printSvg(contents: String, fields: Map<String, String>)
}