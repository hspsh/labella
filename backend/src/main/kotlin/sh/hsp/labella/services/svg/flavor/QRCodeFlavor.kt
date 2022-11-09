package sh.hsp.labella.services.svg.flavor

import javax.xml.parsers.DocumentBuilderFactory

class QRCodeFlavor : SVGFlavor {
    override fun flavor(svg: String): String {
        val documentBuilder = DocumentBuilderFactory.newInstance()
        val doc = documentBuilder.newDocumentBuilder().parse(svg.byteInputStream())

        val svgRoot = doc.documentElement

        val nodeList = svgRoot.getElementsByTagName("svg:image")

        for (i in 0 until nodeList.length) {
            val image = nodeList.item(i)
            val qrCode = image.attributes.getNamedItem("qrCode")
            if (qrCode != null) {
                val width = image.attributes.getNamedItem("width").textContent.toInt()
                val height = image.attributes.getNamedItem("height").textContent.toInt()


            }
        }
        return ""
    }
}