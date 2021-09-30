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
public class SwaggerConfig {
    //3.0.0 http://localhost:8088/api/swagger-ui/index.html

    RequestParameterBuilder tokenBuilder = new RequestParameterBuilder();
    List<RequestParameter> headers = new ArrayList<>();

    private final ApiInfo apiInfo = new ApiInfoBuilder()
        .title("Project/Study 매칭 website")
        .description("프로젝트 메인 API")
        .contact(new Contact("Name", "https://naver.com", "my@email.com"))
        .license("MIT License")
        .version("5.0")
        .build();

    // 하단의 코드는 글로벌한 설정이 필요할 때 주석해제하시고 추가하시면 되겠습니다.
    //swagger 내에서 글로벌하게 적용되어야 하는 헤더가 필요하다면 RequestParameterBuilder 하나 더 만들어서
    //aParameters에 추가하기.
    public SwaggerConfig() {
        tokenBuilder
            .name("Authorization")
            .description("jwtToken here")
            .required(false)
            .in("header")
            .accepts(Collections.singleton(MediaType.APPLICATION_JSON));
        headers.add(tokenBuilder.build());
    }
//    @Bean public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .securitySchemes(singletonList(apiKey())) // 토큰을 읽기위한 정보를 넘겨준다
//            .securityContexts(singletonList(securityContext())) // SecurityContext 를 넘겨준다
//
//            .consumes(singleton("application/json"))
//            .useDefaultResponseMessages(false)
//            .select()
//            .apis(withMethodAnnotation(ApiOperation.class))
//            .build();
//        }

    @Bean
    public Docket mainApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .globalRequestParameters(headers) // 글로벌 파라미터 필요시 추가하기
            .apiInfo(apiInfo)
            .groupName("Member")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.member.controller"))
            // api 필요한 class path 추가
            .paths(
                        PathSelectors.ant("/**/member/**")
                        .or(PathSelectors.ant("/**/auth/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket chatApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .globalRequestParameters(headers) // 글로벌 파라미터 필요시 추가하기
            .apiInfo(apiInfo)
            .groupName("Chat")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.chat.controller"))
            // api 필요한 클래스패스 추가하기
            .paths(
                        PathSelectors.ant("/**/chat/**")
//						.or(PathSelectors.ant("/**/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket groupApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//            .globalRequestParameters(aParameters) // 글로벌 파라미터 필요시 추가하기
            .globalRequestParameters(headers)
            .apiInfo(apiInfo)
            .groupName("Group")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.group.controller"))

            // api 필요한 클래스패스 추가하기
            .paths(
                PathSelectors.ant("/**/study/**")
                    .or(PathSelectors.ant("/**/studyapplication/**"))
                    .or(PathSelectors.ant("/**/project/**"))
                    .or(PathSelectors.ant("/**/projectapplication/**"))
                    .or(PathSelectors.ant("/**/club/**"))
                    .or(PathSelectors.ant("/**/clubapplication/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket studyBoardApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(headers)
                .apiInfo(apiInfo)
                .groupName("StudyBoard")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.group.studyboard.board.controller"))
                .paths(
                        PathSelectors.ant("/**/study/board/**")
                )
                .build()
                .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket studyBoardArticleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalRequestParameters(headers)
                .apiInfo(apiInfo)
                .groupName("StudyArticle")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.group.studyboard.article.controller"))
                .paths(
                        PathSelectors.ant("/**/study/board/**")
                )
                .build()
                .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket fileApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//            .globalRequestParameters(aParameters) // 글로벌 파라미터 필요시 추가하기
            .globalRequestParameters(headers)
            .apiInfo(apiInfo)
            .groupName("File")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ssafy.match.file.controller"))
            // api 필요한 클래스패스 추가하기
            .paths(
                PathSelectors.ant("/**/file/**")
//                    .or(PathSelectors.ant("/**/projectform/**"))
//                PathSelectors.any()
            )
            .build()
            .useDefaultResponseMessages(false);
    }
}
