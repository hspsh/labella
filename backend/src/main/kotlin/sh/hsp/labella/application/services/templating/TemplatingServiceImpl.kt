package sh.hsp.labella.application.services.templating

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.application.ports.TemplateRepository
import sh.hsp.labella.model.ports.TemplateService

class TemplatingServiceImpl(
    val templateRepository: TemplateRepository,
    val templateService: TemplateService
) : TemplatingService {
    override fun template(templateId: Long, fields: Map<String, String>): TemplatedDTO {

        val template = templateRepository.findById(templateId) ?: throw ResourceNotFoundException()
        val render =
            template
                .renderTemplate(
                    fields,
                    templateService
                )

        return TemplatedDTO(render, template.name ?: "unknown")
    }


    override fun fields(templateId: Long): Set<String> {
        val template = templateRepository.findById(templateId) ?: throw ResourceNotFoundException()

        return template.extractFieldNamesFromTemplate { contents -> templateService.listFields(contents) }
    }
}