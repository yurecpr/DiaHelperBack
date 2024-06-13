package de.ait_tr.DiaHelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DiaHelperProjectApplication {
//	@Bean//udalit posle SpringSecurity
//	public BCryptPasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}
	public static void main(String[] args) {
		SpringApplication.run(DiaHelperProjectApplication.class, args);
	}
}