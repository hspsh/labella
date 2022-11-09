package sh.hsp.labella.services.svg

import sh.hsp.labella.model.PrintDimensions

interface SvgSizeExtractor {
    fun extract(svg: String): PrintDimensions?
}