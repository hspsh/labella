package sh.hsp.labella.infra

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

class BufferedImageSerializer : StdSerializer<BufferedImage>(BufferedImage::class.java) {
    override fun serialize(value: BufferedImage?, jgen: JsonGenerator, provider: SerializerProvider?) {
        value?.let {
            val buff = ByteArrayOutputStream()
            ImageIO.write(it, "PNG", buff)
            val b64str = Base64.getEncoder().encodeToString(buff.toByteArray())
            jgen.writeString(b64str)
        }
    }
}