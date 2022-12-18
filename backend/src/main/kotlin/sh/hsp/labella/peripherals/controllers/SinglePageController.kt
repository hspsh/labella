package sh.hsp.labella.peripherals.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class SinglePageController() {
    @GetMapping("/template/add")
    fun add() = "/template/add.html"

    @GetMapping("/template/edit/{id:[0-9]+}")
    fun edit() = "/template/edit/[id].html"

    @GetMapping("/template/print/{id:[0-9]+}")
    fun print() = "/template/print/[id].html"
}