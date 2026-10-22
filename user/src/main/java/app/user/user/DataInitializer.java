package app.user.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        return args -> {
            User user1 = new User();
            user1.setExternalUserId(UUID.fromString("a963d9c1-4094-420b-8c40-1b16d3131d2c"));
            user1.setUserName("FighterZ");
            user1.setFirstName("Paul");
            user1.setLastName("Goodman");
            user1.setEmail("paulg2001@gmail.com");
            user1.setPassword("password123");
            user1.setPhoneNumber("46578123");

            User user2 = new User();
            user2.setExternalUserId(UUID.fromString("9abd6f56-9a62-4f25-96c8-afbdf742aa0c"));
            user2.setUserName("jane_smith22");
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setEmail("jane@example.com");
            user2.setPassword("secret456");
            user2.setPhoneNumber("0987654321");

            User user3 = new User();
            user3.setExternalUserId(UUID.fromString("92d82076-01a3-41ae-89ed-7bc138bde54f"));
            user3.setUserName("Kale88");
            user3.setFirstName("jordan");
            user3.setLastName("sheit");
            user3.setEmail("manecmon@hotmail.com");
            user3.setPassword("whatthadogdoin");
            user3.setPhoneNumber("9888838");

            User user4 = new User();
            user4.setExternalUserId(UUID.fromString("82df388a-a1cd-47c0-9e6d-78d5b6bbbf7d"));
            user4.setUserName("JavaMan");
            user4.setFirstName("Grant");
            user4.setLastName("Hill");
            user4.setEmail("shamrock4000@gmail.com");
            user4.setPassword("LBJ");
            user4.setPhoneNumber("43245644");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);

            System.out.println("Sample H2 data initialized!");
        };
    }
}
