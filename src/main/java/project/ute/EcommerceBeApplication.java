package project.ute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class EcommerceBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBeApplication.class, args);
	}
}
