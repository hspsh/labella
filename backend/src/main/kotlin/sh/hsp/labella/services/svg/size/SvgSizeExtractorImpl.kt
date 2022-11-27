package sh.hsp.labella.services.svg.size

import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.SvgSizeExtractor
import javax.xml.parsers.DocumentBuilderFactory

class SvgSizeExtractorImpl : SvgSizeExtractor {
    override fun extract(svg: String): PrintDimensions? {
        val documentBuilder = DocumentBuilderFactory.newInstance()
        val doc = documentBuilder.newDocumentBuilder().parse(svg.byteInputStream())

        val svgRoot = doc.documentElement

        return runCatching<PrintDimensions> {
            val width = svgRoot.getAttribute("width").toInt()
            val height = svgRoot.getAttribute("height").toInt()
            return PrintDimensions(width, height)
        }.getOrNull()
    }
}