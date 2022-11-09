package sh.hsp.labella.services.renderer

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


class RendererServiceImpl : RendererService {

    override fun render(renderingInput: RenderingInput): RenderingOutput =
        when (renderingInput) {
            is RenderingInput.SVGRenderingInput -> renderSvg(renderingInput)
            is RenderingInput.MdRenderingInput -> throw UnsupportedOperationException("Markdown not supported yet")
        }

    private fun renderSvg(input: RenderingInput.SVGRenderingInput): RenderingOutput {

        val dimens = input.printDimensions
        val cmdOutputStream = ByteArrayOutputStream()
        val pipe = Pipe(input.content.byteInputStream(), cmdOutputStream)

        val op = IMOperation().apply {
            addImage("svg:-")
            resize(dimens.xInPixels, dimens.yInPixel)
            background("white")
            gravity("center")
            extent(dimens.xInPixels, dimens.yInPixel)
            addImage("png:-")
        }

        ConvertCmd().apply {
            setInputProvider(pipe)
            setOutputConsumer(pipe)
            run(op)
        }

        val bufferedImageStream = ByteArrayInputStream(cmdOutputStream.toByteArray())
        val bufferedImage: BufferedImage = ImageIO.read(bufferedImageStream)

        return RenderingOutput(bufferedImage)
    }

}