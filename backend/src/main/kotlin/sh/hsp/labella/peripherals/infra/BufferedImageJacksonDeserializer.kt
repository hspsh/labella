package sh.hsp.labella.peripherals.infra

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.util.*
import javax.imageio.ImageIO

class BufferedImageDeserializer : StdDeserializer<BufferedImage>(BufferedImage::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): BufferedImage {
        val base64Image = p?.readValueAs(String::class.java)
        val byteArray = Base64.getDecoder().decode(base64Image)
        val image = ImageIO.read(ByteArrayInputStream(byteArray))

        return image
    }
}