package com.progressSoft.taskProgressSoft.config.swagger;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SpringFoxConfig {


    @Bean
    public Docket productApi() {

        SwaggerTags swaggerTag = SwaggerTags.values()[0];
        Tag firstTag = new Tag(swaggerTag.name(), swaggerTag.getDescription());

        Tag[] tags = Arrays.stream(SwaggerTags.values()).skip(3).map(s -> new Tag(s.name(), s.getDescription())).toArray(Tag[]::new);

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.progressSoft.taskProgressSoft")).build()
                .tags(firstTag, tags).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API").version("1.0.0").build();
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
