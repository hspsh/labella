package sh.hsp.labella.services.renderer.svg

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.RenderingInput

class MultipleSVGRenderingServiceImplTest {
    val renderer = MultipleSVGRenderingServiceImpl(InkscapeRendererService())

    @Test
    fun parseOneFile() {
        val content = this.javaClass.getResource("img.svg").readText()

        val output = renderer.renderAll(RenderingInput.SVGRenderingInput(content, PrintDimensions.ORANGE_LABEL))

        assertEquals(output.size, 1)
    }

    @Test
    fun parseTwoFilesOneFile() {
        val content = this.javaClass.getResource("images.svg").readText()

        val output = renderer.renderAll(RenderingInput.SVGRenderingInput(content, PrintDimensions.ORANGE_LABEL))

        assertEquals(output.size, 2)
    }
}