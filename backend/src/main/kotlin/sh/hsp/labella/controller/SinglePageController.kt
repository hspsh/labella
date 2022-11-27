package sh.hsp.labella.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.services.previewing.PreviewingService
import sh.hsp.labella.services.printing.PrintingService
import java.awt.image.BufferedImage

@Controller
class SinglePageController() {
    @GetMapping("/template/add")
    fun add() = "/template/add.html"

    @GetMapping("/template/edit/{id:[0-9]+}")
    fun edit() = "/template/edit/[id].html"

    @GetMapping("/template/print/{id:[0-9]+}")
    fun print() = "/template/print/[id].html"
}