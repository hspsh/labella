package sh.hsp.labella.peripherals.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sh.hsp.labella.application.services.templating.TemplatingService
import sh.hsp.labella.peripherals.adapters.SpringTemplateRepository


@RestController
@RequestMapping(path = ["/api/templates/{templateId}"])
class DownloadController(val repository: SpringTemplateRepository, val templatingService: TemplatingService) {

    @GetMapping(produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE], path = ["/download"])
    fun download(@PathVariable templateId: Long): ResponseEntity<String> {
        val template = repository.findById(templateId).get()

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${template.name}.svg\"")
            .body(template.template);
    }

    //TODO: TEST!!!
    @GetMapping(produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE], path = ["/templated/download"])
    fun download(@PathVariable templateId: Long, @RequestParam fields: Map<String, String>): ResponseEntity<String> {
        val template = templatingService.template(templateId, fields)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${template.fileName}.svg\"")
            .body(template.templated.templated);
    }
}