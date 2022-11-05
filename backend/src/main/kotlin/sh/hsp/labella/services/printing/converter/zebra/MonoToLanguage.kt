package sh.hsp.labella.services.printing.converter.zebra

import sh.hsp.labella.services.printing.converter.mono.Mono

interface MonoToLanguage {
    fun convert(mono: Mono): ByteArray
}