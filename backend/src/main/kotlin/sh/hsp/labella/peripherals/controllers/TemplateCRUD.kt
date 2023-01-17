package sh.hsp.labella.peripherals.controllers

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.web.bind.annotation.CrossOrigin
import sh.hsp.labella.application.ports.TemplateRepository
import sh.hsp.labella.model.Template

@CrossOrigin(origins = ["*"])


interface SpringTemplateRepository : PagingAndSortingRepository<Template, Long> {
    fun findAllByNameContaining(name: String?, paging: Pageable): Page<Template>
}