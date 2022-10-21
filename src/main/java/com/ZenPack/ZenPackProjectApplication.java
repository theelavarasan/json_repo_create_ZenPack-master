package com.ZenPack;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
@ComponentScan
public class ZenPackProjectApplication extends SpringBootServletInitializer {
	
/*	@Value("${postgresurl}")
	private  String server;*/

	public static void main(String[] args) {
		SpringApplication.run(ZenPackProjectApplication.class, args);
	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ZenPack")).build();
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
/*	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException, KeeperException, InterruptedException
	{
		ZookeeperConnection zkConnection = new ZookeeperConnection();
		//ZKModel.zkData = zkConnection.getZKData();

		Utilities utilites = new Utilities();
		//DataSourceConnection dataSource = new DataSourceConnection();

	}*/
	
/*	@PostConstruct
	public void print() {
		System.out.println(server);
	}
    
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/*").allowedOrigins("*");
			}
		};
	}*/
/*	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializerByType(PagedList.class, new PagedListSerializer());
		return builder;
	}*/

}
