package com.candidatemobileapi.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.candidatemobileapi.util.CandidateMobileApiConstants;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class CandidateMobileApiSwaggerConfig {

	@Bean
	public Docket candidateMobileApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage(CandidateMobileApiConstants.BASE_PACKAGE))
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				CandidateMobileApiConstants.CANDIDATES_MOBILE_API,
				CandidateMobileApiConstants.APP_DESC, "", "", new Contact("Riddhi",
						"", ""), "", "");
		return apiInfo;
	}

}
