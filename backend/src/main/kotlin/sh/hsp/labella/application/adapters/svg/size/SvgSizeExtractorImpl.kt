package sh.hsp.labella.application.adapters.svg.size

import org.w3c.dom.Element
import org.w3c.dom.NodeList
import sh.hsp.labella.application.adapters.svg.flavor.RewritingCodeFlavor
import sh.hsp.labella.model.PrintDimensions
import sh.hsp.labella.model.ports.SvgSizeExtractor
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants

class SvgSizeExtractorImpl : SvgSizeExtractor {
    override fun extract(svg: String): PrintDimensions? {
        val documentBuilder = DocumentBuilderFactory.newInstance()
        val doc = documentBuilder.newDocumentBuilder().parse(svg.byteInputStream())

        val rootSvgs = RewritingCodeFlavor.xPath.compile("//svg")
        val nodes = rootSvgs.evaluate(doc, XPathConstants.NODESET) as NodeList
        val svgRoot = nodes.item(0) as Element

        return runCatching<PrintDimensions> {
            val width = svgRoot.getAttribute("width").toInt()
            val height = svgRoot.getAttribute("height").toInt()
            return PrintDimensions(width, height)
        }.getOrNull()
    }
}