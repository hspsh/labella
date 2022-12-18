package sh.hsp.labella.application.adapters.printer.converter.zebra

import sh.hsp.labella.application.adapters.printer.converter.mono.Mono

interface MonoToLanguage {
    fun convert(mono: Mono): ByteArray
}