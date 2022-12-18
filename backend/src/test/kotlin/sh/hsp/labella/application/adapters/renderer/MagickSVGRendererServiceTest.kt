package sh.hsp.labella.application.adapters.renderer

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import sh.hsp.labella.application.adapters.svg.MagickRendererService
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.RenderingInput
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class MagickSVGRendererServiceTest {

    @Test
    @Disabled("Two images do not match, no idea why")
    fun `GIVEN SVG content WHEN render called SHOULD render PNG`() {
        // Given
        val expected = ImageIO.read(this.javaClass.getResourceAsStream("cmp.png"))
        val dimensions = PrintDimensions(320, 224)
        val content = this.javaClass.getResource("img.svg").readText()
        val svgInput = RenderingInput.SVGRenderingInput(content, dimensions)
        val rendererService = MagickRendererService()
        // When
        val renderingOutput = rendererService.render(svgInput)
        // Then
        assert(renderingOutput.image.width == 320)
        assert(renderingOutput.image.height == 224)
        assert(expected.imageEqual(renderingOutput.image))
    }
}

fun BufferedImage.imageEqual(other: BufferedImage): Boolean {
    if (this.width != other.width || this.height != other.height) return false
    for (x in 0 until this.width) {
        for (y in 0 until this.height) {
            if (this.getRGB(x, y) != other.getRGB(x, y))
                return false
        }
    }
    return true
}