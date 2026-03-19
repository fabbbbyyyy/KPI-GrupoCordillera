package cl.cordillera.kpi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI kpiOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("KPI Grupo Cordillera API")
                        .description("Documentacion OpenAPI para la gestion de indicadores KPI")
                        .version("v1")
                        .contact(new Contact()
                                .name("Equipo KPI Grupo Cordillera")
                                .email("soporte@grupocordillera.cl")));
    }
}
