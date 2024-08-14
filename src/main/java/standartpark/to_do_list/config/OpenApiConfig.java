package standartpark.to_do_list.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tasksMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("ToDo_list")
                        .description("Test task for standartpark")
                        .version("1.0"));
    }
}