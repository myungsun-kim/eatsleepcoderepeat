package com.ssafy.match.config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //3.0.0 http://localhost:8088/api/swagger-ui/index.html

    RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
    List<RequestParameter> aParameters = new ArrayList<>();

    private final ApiInfo apiInfo = new ApiInfoBuilder()
        .title("스터디 매칭 프로그램")
        .description("프로젝트 메인 API입니다")
        .contact(new Contact("SSAFY_5th_D105", "https://ssafy.com", "no@email.com"))
        .license("MIT License")
        .version("5.0")
        .build();

    // 하단의 코드는 글로벌한 설정이 필요할 때 주석해제하시고 추가하시면 되겠습니다.
    //swagger 내에서 글로벌하게 적용되어야 하는 헤더가 필요하다면 RequestParameterBuilder 하나 더 만들어서
    //aParameters에 추가하기.
//    public SwaggerConfig() {
//        tokenBuilder
//            .name("auth-token")
//            .description("auth-t2oken")
//            .required(false)
//            .in("header")
//            .accepts(Collections.singleton(MediaType.APPLICATION_JSON));
//        aParameters.add(tokenBuilder.build());
//    }

    @Bean
    public Docket mainApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//            .globalRequestParameters(aParameters) // 글로벌 파라미터 필요시 추가하기
            .apiInfo(apiInfo)
            .groupName("Chat")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.controller"))
            // api 필요한 클래스패스 추가하기
            .paths(
                        PathSelectors.ant("/**/member/**")
//						.or(PathSelectors.ant("/**/chat/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket chatApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//            .globalRequestParameters(aParameters) // 글로벌 파라미터 필요시 추가하기
            .apiInfo(apiInfo)
            .groupName("All controller")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.chat.controller"))
            // api 필요한 클래스패스 추가하기
            .paths(
                        PathSelectors.ant("/**/member/**")
                        .or(PathSelectors.ant("/**/auth/**"))
//						.or(PathSelectors.ant("/**/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }
}
