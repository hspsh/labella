package sh.hsp.labella.services.templating

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import sh.hsp.labella.controller.TemplateRepository
import sh.hsp.labella.model.TemplateService
import sh.hsp.labella.model.Templated

class TemplatingServiceImpl(
    val templateRepository: TemplateRepository,
    val templateService: TemplateService
) : TemplatingService {
    override fun template(templateId: Long, fields: Map<String, String>): TemplatedDTO {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        val render =
            template
                .template(
                    fields,
                    templateService
                )

        return TemplatedDTO(render, template.name ?: "unknown")
    }


    override fun fields(templateId: Long): List<String> {
        val maybeTemplate = templateRepository.findById(templateId)
        if (maybeTemplate.isEmpty) {
            throw ResourceNotFoundException()
        }

        val template = maybeTemplate.get()

        return template.fields { contents -> templateService.listFields(contents) }
    }
}