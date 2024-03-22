package com.mrg.onboarding;


import com.mrg.onboarding.security.RsaKeyProperties;
import com.mrg.onboarding.user.AppUser;
import com.mrg.onboarding.user.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class OnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(AppUserService userService, PasswordEncoder encoder) {
		return args -> {
			AppUser user1 = new AppUser();
			user1.setUsername("user1");
			user1.setName("User1Name");
			user1.setSurname("User1Surname");
			user1.setEmail("user1@gmail.com");
			user1.setPassword(encoder.encode("password"));
			user1.setRoles("ROLE_USER, ROLE_ADMIN");
			userService.createUser(user1);

			AppUser user2 = new AppUser();
			user2.setUsername("user2");
			user2.setName("User2Name");
			user2.setSurname("User2Surname");
			user2.setEmail("user2@gmail.com");
			user2.setPassword(encoder.encode("password"));
			user2.setRoles("ROLE_USER");
			userService.createUser(user2);
		};
	}
}
