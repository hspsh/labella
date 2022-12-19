package sh.hsp.labella.peripherals

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.filter.ShallowEtagHeaderFilter
import sh.hsp.labella.peripherals.infra.BufferedImageDeserializer
import sh.hsp.labella.peripherals.infra.BufferedImageSerializer
import java.awt.image.BufferedImage
import javax.servlet.Filter


@Configuration
class InfraConfiguration {
    @Bean
    fun bufferedImageHttpMessageConverter(): HttpMessageConverter<BufferedImage> = BufferedImageHttpMessageConverter()

    @Bean
    fun shallowEtagHeaderFilter(): Filter? {
        return ShallowEtagHeaderFilter()
    }

    @Bean
    fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter(
            Jackson2ObjectMapperBuilder
                .json()
                .serializers(BufferedImageSerializer())
                .deserializers(BufferedImageDeserializer())
                .build()
        )
    }
}