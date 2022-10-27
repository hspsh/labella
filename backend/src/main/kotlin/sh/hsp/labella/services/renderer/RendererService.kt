package sh.hsp.labella.services.renderer

import java.awt.image.BufferedImage

interface RendererService {
    fun render(renderingInput: RenderingInput): RenderingOutput
}


data class PrinterConfiguration(
        val dpi: Int
);

sealed class RenderingInput {
    data class SVGRenderingInput(val SVGContents: String);
    data class MdRenderingInput(val MdContents: String);
}

data class RenderingOutput(val image: BufferedImage, val printDimensions: PrintDimensions)
data class PrintDimensions(val xInMillimeters: Double, val yInMillimeters: Double)