package sh.hsp.labella.services.label

import org.springframework.beans.factory.annotation.Value
import sh.hsp.labella.model.LabelSizeProvider
import sh.hsp.labella.model.PrintDimensions

class FixedLabelSizeProvider(
    val width: Int,
    val height: Int
) : LabelSizeProvider {
    override fun provideLabelSize(svgDimensions: PrintDimensions): PrintDimensions {
        return PrintDimensions(width, height)
    }
}