package sh.hsp.labella.model

import sh.hsp.labella.model.PrintDimensions

interface SvgSizeExtractor {
    fun extract(svg: String): PrintDimensions?
}