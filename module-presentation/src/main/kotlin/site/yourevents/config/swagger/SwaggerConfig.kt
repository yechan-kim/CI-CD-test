package site.yourevents.config.swagger

import DisableSwaggerSecurity
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.web.method.HandlerMethod


class SwaggerConfig {
    @Value("\${app.server.url}")
    private val serverUrl: String? = null

    @Bean
    fun openAPI(): OpenAPI {
        val jwt = "JWT"
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList(jwt)

        val components = Components().addSecuritySchemes(
            jwt, SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        )

        return OpenAPI()
            .addServersItem(Server().url(serverUrl))
            .components(components)
            .info(apiInfo())
            .addSecurityItem(securityRequirement)
    }

    @Bean
    fun customize(): OperationCustomizer {
        return OperationCustomizer { operation: Operation, handlerMethod: HandlerMethod ->
            val methodAnnotation = handlerMethod.getMethodAnnotation(
                DisableSwaggerSecurity::class.java
            )
            if (methodAnnotation != null) {
                operation.security = emptyList()
            }
            operation
        }
    }

    private fun apiInfo(): Info {
        return Info()
            .title("test API")
            .description("test for CI-CD")
            .version("1.0.0")
    }
}
