package sh.hsp.labella.controller

import org.springframework.data.repository.CrudRepository
import sh.hsp.labella.model.Template

interface TemplateRepository: CrudRepository<Template, Long>{
}