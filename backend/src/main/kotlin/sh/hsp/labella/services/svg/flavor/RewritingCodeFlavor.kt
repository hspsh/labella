package sh.hsp.labella.services.svg.flavor

import com.google.zxing.BarcodeFormat
import com.google.zxing.Dimension
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.function.Consumer
import javax.imageio.ImageIO
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory


abstract class RewritingCodeFlavor(forEachMatchedByXPath: String, val modify: Consumer<Node>) : SVGFlavor {
    companion object{
        var xFactory: XPathFactory = XPathFactory.newInstance()
        var xPath: XPath = xFactory.newXPath()
        val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    }

    val xExpression = xPath.compile(forEachMatchedByXPath)

    override fun flavor(svg: String): String {

        val doc = documentBuilder.parse(svg.byteInputStream())
        val nodeList = xExpression.evaluate(doc, XPathConstants.NODESET) as NodeList

        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            modify.accept(node)
        }

        val tf: Transformer = TransformerFactory.newInstance().newTransformer()
        val arr = ByteArrayOutputStream()
        val source = DOMSource(doc)
        val result = StreamResult(arr)
        tf.transform(source, result)

        return String(arr.toByteArray())
    }

    fun toQR(str: String, dim: Dimension): BufferedImage? {
        val qrCodeWriter = QRCodeWriter()
        val encoded = qrCodeWriter.encode(
            str, BarcodeFormat.QR_CODE, dim.width, dim.height,
            mapOf(Pair(EncodeHintType.MARGIN, 0))
        )
        return MatrixToImageWriter.toBufferedImage(encoded, MatrixToImageConfig())
    }

    fun BufferedImage.toBase64(): String {
        val arr = ByteArrayOutputStream()
        ImageIO.write(this, "png", arr)
        return String(Base64.getEncoder().encode(arr.toByteArray()))
    }
}