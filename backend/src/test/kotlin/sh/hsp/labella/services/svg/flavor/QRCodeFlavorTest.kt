package sh.hsp.labella.services.svg.flavor

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sh.hsp.labella.services.svg.flavor.flavors.QRCodeFlavor

class QRCodeFlavorTest() {

    @Test
    fun test() {
        val qrCodeFlavor = QRCodeFlavor()
        val svg = this.javaClass.getResource("qrCode.svg").readText()
        val svgOutput = this.javaClass.getResource("qrCoded.svg").readText()

        val output = qrCodeFlavor.flavor(svg)

        assertEquals(output, svgOutput)
    }
}