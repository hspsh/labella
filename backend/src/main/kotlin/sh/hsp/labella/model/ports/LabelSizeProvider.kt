package sh.hsp.labella.model.ports

import sh.hsp.labella.model.PrintDimensions

interface LabelSizeProvider {
    fun provideLabelSize(svgDimensions: PrintDimensions): PrintDimensions
}