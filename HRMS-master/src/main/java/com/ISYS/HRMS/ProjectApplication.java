package com.ISYS.HRMS;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@OpenAPIDefinition(
		servers = @Server(
				url = "${HTTP}"
		),
		info = @Info(
				title = "My Swagger Documentation - Open Source API",
				version = "1.0",
				description = "Swagger Documentation - Open Source API")
)
@EnableAsync
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
