package com.mrg.onboarding;


import com.mrg.onboarding.security.RsaKeyProperties;
import com.mrg.onboarding.security.auth.AppUser;
import com.mrg.onboarding.security.auth.UserRepository;
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
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			AppUser user1 = new AppUser();
			user1.setUsername("user1");
			user1.setEmail("user1@gmail.com");
			user1.setPassword(encoder.encode("password"));
			user1.setRoles("ROLE_USER, ROLE_ADMIN");
			userRepository.save(user1);

			AppUser user2 = new AppUser();
			user2.setUsername("user2");
			user2.setEmail("user2@gmail.com");
			user2.setPassword(encoder.encode("password"));
			user2.setRoles("ROLE_USER");
			userRepository.save(user2);
		};
	}
}
