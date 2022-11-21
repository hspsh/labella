package sh.hsp.labella.services.renderer

import com.sun.istack.logging.Logger
import sh.hsp.labella.model.RendererService
import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput
import java.io.File
import javax.imageio.ImageIO

class InkscapeRendererService : RendererService {
    val logger = Logger.getLogger(InkscapeRendererService::class.java)

    constructor() {
        if (Runtime.getRuntime().exec("inkscape --version").waitFor() != 0) {
            throw IllegalStateException("Inkscape must be installed")
        }
    }

    override fun render(renderingInput: RenderingInput): RenderingOutput =
        when (renderingInput) {
            is RenderingInput.SVGRenderingInput -> renderSvg(renderingInput)
            is RenderingInput.MdRenderingInput -> throw UnsupportedOperationException("Markdown not supported yet")
        }

    fun renderSvg(renderingInput: RenderingInput.SVGRenderingInput): RenderingOutput {
        val inputFile = File.createTempFile("labella", ".svg")
        inputFile.writeText(renderingInput.content)

        val outputFile = File.createTempFile("labella", ".png")

        try {
            val inkscape = Runtime.getRuntime()
                .exec(
                    arrayOf(
                        "inkscape",
                        "--export-background=white",
                        "--export-width=${renderingInput.printDimensions.xInPixels}",
                        "--export-height=${renderingInput.printDimensions.yInPixel}",
                        "--export-png=${outputFile.absolutePath}",
                        inputFile.absolutePath
                    )
                )
            val executed = inkscape.waitFor();
            logger.info("Inkscape exited with: $executed")

            val image = ImageIO.read(outputFile)
            return RenderingOutput(image)
        } finally {
            inputFile.delete()
            outputFile.delete()
        }
    }
}