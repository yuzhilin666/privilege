package com.imooc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	
	// http://localhost:8080/swagger-ui.html	原访问路径
	// http://localhost:8080/doc.html   		友好体验版的访问路径
	
	//swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	//构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("支付宝小程序实战 - 666Miss 在线api")
				//创建人
				.contact(new Contact("慕课网 - 风间影月", "这里写课程正式发布的地址", null))
				// 描述
				.description("专为 &nbsp; <a href='https://coding.imooc.com/class/293.html' target='_blank'>[666Miss]</a>提供的在线文档，可根据各个api接口的规范来调用各种接口~~")
				//版本号
				.version("1.0")
				.termsOfServiceUrl("https://coding.imooc.com/class/293.html")
				.build();
	}
}
