package sh.hsp.labella.services.svg.flavor.flavors

import com.google.zxing.BarcodeFormat
import com.google.zxing.Dimension
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import sh.hsp.labella.services.svg.flavor.RewritingCodeFlavor
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO


class QRCodeFlavor : RewritingCodeFlavor("//image[@qrCode]", {
    val image = it
    val qrCode = image.attributes.getNamedItem("qrCode").textContent
    val width = image.attributes.getNamedItem("width").textContent.toDouble()
    val height = image.attributes.getNamedItem("height").textContent.toDouble()

    image.attributes.getNamedItem("xlink:href").textContent =
        "data:image/png;base64,${toQR(qrCode, Dimension(width.toInt(), height.toInt()))?.toBase64()}"
})

fun toQR(str: String, dim: Dimension): BufferedImage? {
    val qrCodeWriter = QRCodeWriter()
    val encoded = qrCodeWriter.encode(
        str, BarcodeFormat.QR_CODE, dim.width, dim.height,
        mapOf(
            Pair(EncodeHintType.MARGIN, 0),
            Pair(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q)
        )
    )
    return MatrixToImageWriter.toBufferedImage(encoded, MatrixToImageConfig())
}

fun BufferedImage.toBase64(): String {
    val arr = ByteArrayOutputStream()
    ImageIO.write(this, "png", arr)
    return String(Base64.getEncoder().encode(arr.toByteArray()))
}
