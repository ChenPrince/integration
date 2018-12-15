package com.chenjy.integrate.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//启动的
@SpringBootApplication()
//swagger的
@EnableSwagger2
//找接口的
@ComponentScan(basePackages = {"com.chenjy.integrate.demo", "com.chenjy.integrate.demo.serviceImpl"})
@MapperScan(value = "com.chenjy.integrate.demo.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket buildDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())//调用下面的apiInfo()方法
				.select()
				//Controller所在路径
				.apis(RequestHandlerSelectors.basePackage("com.chenjy.integrate.demo.controller"))
				.paths(PathSelectors.any())
				.build();


	}

	/**
	 * 构造ui信息
	 * @return
	 */
	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("springboot swagger2(Restful API) mybatis 整合")
				.description("首版")
				.termsOfServiceUrl("www.baidu.com")
				.contact(new Contact("chenjy","www.baidu.com","1163005753@qq.com"))
				.version("0.0.1")
				.build();

	}


	@Bean
	public SecurityScheme apiKey() {
		return new ApiKey("access_token", "accessToken", "header");
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
