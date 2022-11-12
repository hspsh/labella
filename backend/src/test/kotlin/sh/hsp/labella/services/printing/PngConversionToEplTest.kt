package sh.hsp.labella.services.printing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import sh.hsp.labella.services.printer.converter.mono.SimpleImageToMono
import sh.hsp.labella.services.printer.converter.zebra.MonoToEpl
import javax.imageio.ImageIO

class PngConversionToEplTest {
    @Test
    @Disabled("EPL changed")
    fun shouldGenerateSameEPL() {
        val imageToMono = SimpleImageToMono()
        val monoToLanguage = MonoToEpl()

        val image = ImageIO.read(this.javaClass.getResourceAsStream("img2.png"))
        val data = imageToMono.convert(image)
        val language = monoToLanguage.convert(
            data
        )

        val oldLanguage = this.javaClass.getResourceAsStream("output.epl")?.readAllBytes()?.asList()?.toByteArray()

        Assertions.assertEquals(language.asList(), oldLanguage!!.asList())
    }
}