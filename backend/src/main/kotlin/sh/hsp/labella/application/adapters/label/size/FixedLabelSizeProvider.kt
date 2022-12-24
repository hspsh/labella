package sh.hsp.labella.application.adapters.label.size

import sh.hsp.labella.model.ports.LabelSizeProvider
import sh.hsp.labella.model.PrintDimensions

class FixedLabelSizeProvider(
    val width: Int,
    val height: Int
) : LabelSizeProvider {
    override fun provideLabelSize(svgDimensions: PrintDimensions): PrintDimensions {
        return PrintDimensions(width, height)
    }
}