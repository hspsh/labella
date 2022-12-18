package sh.hsp.labella.model.ports

import sh.hsp.labella.model.PrintDimensions

interface SvgSizeExtractor {
    fun extract(svg: String): PrintDimensions?
}