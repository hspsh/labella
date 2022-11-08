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

        val cmd = ConvertCmd()
        val cmdInputStream = input.content.byteInputStream()
        val cmdOutputStream = ByteArrayOutputStream()
        val pipe = Pipe(cmdInputStream, cmdOutputStream)

        val op = IMOperation()
        op.addImage("svg:-")
        op.resize(dimens.xInPixels, dimens.yInPixel)
        op.addImage("png:-")

        cmd.setInputProvider(pipe)
        cmd.setOutputConsumer(pipe)
        cmd.run(op)

        val bufferedImageStream = ByteArrayInputStream(cmdOutputStream.toByteArray())
        val bufferedImage: BufferedImage = ImageIO.read(bufferedImageStream)

        return RenderingOutput(bufferedImage)
    }

}