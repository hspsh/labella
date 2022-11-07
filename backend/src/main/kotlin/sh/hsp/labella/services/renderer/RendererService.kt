package sh.hsp.labella.services.renderer

import java.awt.image.BufferedImage

interface RendererService {
    fun render(renderingInput: RenderingInput): RenderingOutput


}


data class PrinterConfiguration(
    val dpi: Int
);

sealed class RenderingInput {
    data class SVGRenderingInput(val content: String, val printDimensions: PrintDimensions) : RenderingInput()
    data class MdRenderingInput(val content: String, val printDimensions: PrintDimensions) : RenderingInput()
}

data class RenderingOutput(val image: BufferedImage)
data class PrintDimensions(val xInPixels: Int, val yInPixel: Int) {
    companion object {
        val ORANGE_LABEL = PrintDimensions(400, 240)
    }
}