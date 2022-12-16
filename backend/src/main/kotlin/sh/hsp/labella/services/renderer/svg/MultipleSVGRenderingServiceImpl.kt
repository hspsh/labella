package sh.hsp.labella.services.renderer.svg

import org.w3c.dom.NodeList
import sh.hsp.labella.model.MultipleSVGRenderingService
import sh.hsp.labella.model.RenderingInput.SVGRenderingInput
import sh.hsp.labella.model.RenderingOutput
import sh.hsp.labella.model.SVGRendererService
import sh.hsp.labella.services.svg.flavor.RewritingCodeFlavor
import sh.hsp.labella.services.svg.flavor.map
import sh.hsp.labella.services.svg.flavor.writeToString
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants

class MultipleSVGRenderingServiceImpl(val rendererService: SVGRendererService) : MultipleSVGRenderingService {
    override fun renderAll(renderingInput: SVGRenderingInput): List<RenderingOutput> {
        val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = documentBuilder.parse(renderingInput.content.byteInputStream())

        val rootSvgs = RewritingCodeFlavor.xPath.compile("/svg")

        if ((rootSvgs.evaluate(doc, XPathConstants.NODESET) as NodeList).length == 1) {
            return listOf(rendererService.render(renderingInput))
        }

        val xExpression = RewritingCodeFlavor.xPath.compile("/svgs/svg")
        val nodeList = xExpression.evaluate(doc, XPathConstants.NODESET) as NodeList

        return nodeList.map {
            val svg = it.writeToString()
            rendererService.render(SVGRenderingInput(svg, renderingInput.printDimensions))
        }
    }
}