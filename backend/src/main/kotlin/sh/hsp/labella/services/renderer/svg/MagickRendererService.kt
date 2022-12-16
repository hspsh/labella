package sh.hsp.labella.services.renderer.svg

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import sh.hsp.labella.model.SVGRendererService
import sh.hsp.labella.model.RenderingInput
import sh.hsp.labella.model.RenderingOutput
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


class MagickRendererService : SVGRendererService {
    override fun render(input: RenderingInput.SVGRenderingInput): RenderingOutput {

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