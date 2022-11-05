package sh.hsp.labella.services.printing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import sh.hsp.labella.services.printing.converter.mono.SimpleImageToMono
import sh.hsp.labella.services.printing.converter.zebra.MonoToEpl
import sh.hsp.labella.services.printing.converter.zebra.MonoToLanguage
import java.io.FileOutputStream
import javax.imageio.ImageIO

class PngConversionToEplTest {
    @Test
    fun shouldGenerateSameEPL() {
        val imageToMono = SimpleImageToMono()
        val monoToLanguage = MonoToEpl()

        val image = ImageIO.read(this.javaClass.getResourceAsStream("/img2.png"))
        val data = imageToMono.convert(image)
        val language = monoToLanguage.convert(
            data
        )

        val oldLanguage = this.javaClass.getResourceAsStream("/output.epl")?.readAllBytes()?.asList()?.toByteArray()

        Assertions.assertEquals(language.asList(), oldLanguage!!.asList())
    }
}