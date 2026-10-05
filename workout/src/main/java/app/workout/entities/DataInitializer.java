package app.workout.entities;

import app.workout.enums.WorkoutType;
import app.workout.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(WorkoutRepository workoutRepository){
        return args ->{
            Workout workout1 = new Workout();
            workout1.setExternalWorkoutId(UUID.randomUUID());
            workout1.setUserId(UUID.randomUUID());
            workout1.setWorkoutName("Chest Day");
            workout1.setWorkoutDescription("Working on upper body today - specifically chest.");
            workout1.setWorkoutType(WorkoutType.STRENGTH);
            workout1.setWorkoutDateTime(LocalDateTime.now());

            workoutRepository.save(workout1);

            System.out.println("Sample H2 data initialized");
        };
    }
}
