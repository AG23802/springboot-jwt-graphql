package com.example.springbootjwtgraphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJwtGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtGraphqlApplication.class, args);

				Thread t = new Thread(() -> {
					// code you want to run concurrently
					System.out.println("Hello from a separate thread");
				});
	}
}