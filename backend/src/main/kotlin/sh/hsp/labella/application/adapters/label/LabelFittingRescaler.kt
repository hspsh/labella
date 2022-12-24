package sh.hsp.labella.application.adapters.label

import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.ports.LabelRescaler
import sh.hsp.labella.model.ports.LabelSizeProvider
import kotlin.math.min
import kotlin.math.roundToInt

class LabelFittingRescaler(val sizeProvider: LabelSizeProvider) : LabelRescaler {
    override fun rescale(original: PrintDimensions): PrintDimensions {
        val labelSize = sizeProvider.provideLabelSize(original)
        return original.rescale(labelSize)
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