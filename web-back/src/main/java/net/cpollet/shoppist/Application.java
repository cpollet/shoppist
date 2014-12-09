package net.cpollet.shoppist;

import net.cpollet.shoppist.service.DefaultShoppingListService;
import net.cpollet.shoppist.service.ShoppingListService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ShoppingListService shoppingListService() {
		return new DefaultShoppingListService();
	}
}
