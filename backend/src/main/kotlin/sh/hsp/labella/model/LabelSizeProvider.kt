package sh.hsp.labella.model

interface LabelSizeProvider {
    fun provideLabelSize(svgDimensions: PrintDimensions): PrintDimensions
}