package sh.hsp.labella.application.adapters.label

import com.sun.istack.logging.Logger
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.ports.LabelRescaler
import sh.hsp.labella.model.ports.LabelSizeProvider
import kotlin.math.min
import kotlin.math.roundToInt

class LabelFittingRescaler(private val sizeProvider: LabelSizeProvider) : LabelRescaler {

    private val logger = Logger.getLogger(LabelFittingRescaler::class.java)

    override fun rescale(original: PrintDimensions): PrintDimensions {
        logger.info("Original size: ${original.xInPixels}x${original.yInPixel}")

        val labelSize = sizeProvider.provideLabelSize(original)
        logger.info("Size to fit: ${labelSize.xInPixels}x${labelSize.yInPixel}")

        val rescaled = original.rescale(labelSize)
        logger.info("Rescaled: ${rescaled.xInPixels}x${rescaled.yInPixel}")

        return rescaled
    }

    private fun PrintDimensions.rescale(to: PrintDimensions): PrintDimensions {
        val xScale = to.xInPixels.toFloat() / this.xInPixels
        val yScale = to.yInPixel.toFloat() / this.yInPixel

        val scale = min(xScale, yScale)

        return PrintDimensions(
            (this.xInPixels * scale).roundToInt(),
            (this.yInPixel * scale).roundToInt()
        )
    }
}