package mypackage;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import mypackage.exception.FileStorageProperties;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({FileStorageProperties.class})
public class App {

	public static void main(String[] args) {
		
		SpringApplication.run(App.class, args);

	}
	
	@Bean
	public Docket StudentApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("mypackage")).build();
	}

}
