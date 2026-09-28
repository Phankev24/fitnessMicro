package app.user.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.UUID;

@Configuration
@Profile("test")
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        return args -> {
            User user1 = new User();
            user1.setExternalUserId(UUID.randomUUID());
            user1.setUserName("FighterZ");
            user1.setFirstName("Paul");
            user1.setLastName("Goodman");
            user1.setEmail("paulg2001@gmail.com");
            user1.setPassword("password123");
            user1.setPhoneNumber("46578123");

            User user2 = new User();
            user2.setExternalUserId(UUID.randomUUID());
            user2.setUserName("jane_smith");
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setEmail("jane@example.com");
            user2.setPassword("secret456");
            user2.setPhoneNumber("0987654321");

            User user3 = new User();
            user3.setExternalUserId(UUID.randomUUID());
            user3.setUserName("Kale88");
            user3.setFirstName("jordan");
            user3.setLastName("sheit");
            user3.setEmail("manecmon@hotmail.com");
            user3.setPassword("whatthadogdoin");
            user3.setPhoneNumber("9888838");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            System.out.println("Sample H2 data initialized!");
        };
    }
}
