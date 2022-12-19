package sh.hsp.labella.peripherals.adapters

import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.CrossOrigin
import sh.hsp.labella.application.ports.TemplateRepository
import sh.hsp.labella.model.Template

//NASTY KOTLIN BUG :(
@CrossOrigin(origins = ["*"])
interface SpringTemplateRepository : /*TemplateRepository,*/ CrudRepository<Template, Long>  {
}