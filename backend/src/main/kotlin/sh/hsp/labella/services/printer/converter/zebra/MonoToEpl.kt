package sh.hsp.labella.services.printer.converter.zebra

import sh.hsp.labella.services.printer.converter.mono.Mono
import java.nio.charset.Charset

class MonoToEpl : MonoToLanguage {
    override fun convert(mono: Mono): ByteArray {
        val buffer = ArrayList<Byte>()

        buffer.addAll("N\n".toByteArray(Charset.forName("ASCII")).asList())
        buffer.addAll("q${mono.width}\n".toByteArray(Charset.forName("ASCII")).asList())
        buffer.addAll("Q${mono.height}\n".toByteArray(Charset.forName("ASCII")).asList())
        buffer.addAll("GW0,0,${mono.width / 8},${mono.height}\n".toByteArray(Charset.forName("ASCII")).asList())
        buffer.addAll(mono.bitSet.toByteArray().asList())
        buffer.addAll("\nP1\n".toByteArray(Charset.forName("ASCII")).asList())

        return buffer.toByteArray()
    }
}