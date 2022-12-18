package sh.hsp.labella.peripherals.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import sh.hsp.labella.model.Template

@Configuration
class SpringDataRestConfiguration : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(
        config: RepositoryRestConfiguration, cors: CorsRegistry
    ) {
        config.exposeIdsFor(Template::class.java)
    }
}