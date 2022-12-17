package sh.hsp.labella.services.label

import sh.hsp.labella.model.LabelSizeProvider
import sh.hsp.labella.model.PrintDimensions

class SimpleLabelSizeProvider : LabelSizeProvider {
    override fun provideLabelSize(svgDimensions: PrintDimensions): PrintDimensions {
        return svgDimensions
    }
}