package sh.hsp.labella.services.renderer

import org.junit.jupiter.api.Test
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.RenderingInput
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class RendererServiceImplTest {

    @Test
    fun `GIVEN SVG content WHEN render called SHOULD render PNG`() {
        // Given
        val expected = ImageIO.read(this.javaClass.getResourceAsStream("cmp.png"))
        val dimensions = PrintDimensions(200, 200)
        val content = this.javaClass.getResource("img.svg").readText()
        val svgInput = RenderingInput.SVGRenderingInput(content, dimensions)
        val rendererService = RendererServiceImpl()
        // When
        val renderingOutput = rendererService.render(svgInput)
        // Then
        assert(renderingOutput.image.width == 200)
        assert(renderingOutput.image.height == 200)
        assert(bufferedImagesEqual(expected, renderingOutput.image))
    }

    private fun bufferedImagesEqual(img1: BufferedImage, img2: BufferedImage): Boolean {
        if (img1.width != img2.width || img1.height != img2.height) return false
        for (x in 0 until img1.width) {
            for (y in 0 until img1.height) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) return false
            }
        }
        return true
    }

}