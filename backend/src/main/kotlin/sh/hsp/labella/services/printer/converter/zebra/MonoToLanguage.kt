package sh.hsp.labella.services.printer.converter.zebra

import sh.hsp.labella.services.printer.converter.mono.Mono

interface MonoToLanguage {
    fun convert(mono: Mono): ByteArray
}