package app.user.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
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

            userRepository.save(user1);
            userRepository.save(user2);

            System.out.println("Sample H2 data initialized!");
        };
    }
}
