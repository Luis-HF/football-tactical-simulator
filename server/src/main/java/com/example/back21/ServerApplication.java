package com.example.back21;

import org.springframework.boot.SpringApplication;
import com.example.back21.entities.Account;
import com.example.back21.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner test(AccountRepository repository) {
		return args -> {

			Account acc1 = new Account(null, "luis_dev", "lhfspro@gmail.com", "senha123");

			// 2. Persistência: O comando que traduz o objeto para SQL INSERT
			repository.save(acc1);

			System.out.println("--------------------------------------");
			System.out.println("               DEU BOM                ");
			System.out.println("--------------------------------------");
		};
	}
}