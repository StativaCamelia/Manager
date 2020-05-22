package com.taskManager.taskManager.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.schema.ModelRef
import springfox.documentation.builders.ParameterBuilder
import java.util.Collections.singletonList
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    fun api(): Docket {

        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(
                        Collections.singletonList(ParameterBuilder()
                                .name("Authorization")
                                .description("JWT Authorization token")
                                .modelRef(ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()))
    }
}