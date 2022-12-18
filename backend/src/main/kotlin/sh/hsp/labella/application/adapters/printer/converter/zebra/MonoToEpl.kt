package sh.hsp.labella.application.adapters.printer.converter.zebra

import sh.hsp.labella.application.adapters.printer.converter.mono.Mono
import java.nio.charset.Charset

class MonoToEpl : MonoToLanguage {
    override fun convert(mono: Mono): ByteArray {
        val buffer = ArrayList<Byte>()

        buffer.addAll("N\n".bytes())
        buffer.addAll("q${mono.width}\n".bytes())
        buffer.addAll("GW0,0,${mono.width / 8},${mono.height}\n".bytes())
        buffer.addAll(mono.bitSet.toByteArray().asList())
        buffer.addAll("\nP1\n".bytes())

        return buffer.toByteArray()
    }
}

fun String.bytes(): List<Byte> = this.toByteArray(Charset.forName("ASCII")).asList()