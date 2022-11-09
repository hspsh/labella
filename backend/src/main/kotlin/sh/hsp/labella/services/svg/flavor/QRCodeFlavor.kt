package sh.hsp.labella.services.svg.flavor

import sh.hsp.labella.model.PrintDimensions
import javax.xml.parsers.DocumentBuilderFactory

class QRCodeFlavor : SVGFlavor {
    override fun flavor(svg: String): String {
        val documentBuilder = DocumentBuilderFactory.newInstance()
        val doc = documentBuilder.newDocumentBuilder().parse(svg.byteInputStream())

        val svgRoot = doc.documentElement

        val nodeList = svgRoot.getElementsByTagName("svg:image")

        return ""
    }
}