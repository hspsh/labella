package sh.hsp.labella.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(path = ["/api/templates/{templateId}/download"])
class DownloadController(val repository: TemplateRepository) {

    @GetMapping(produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun download(@PathVariable templateId: Long): ResponseEntity<String> {
        val template = repository.findById(templateId).get()

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${template.name}.svg\"")
            .body(template.template);
    }
}