package app.workout.workout;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(WorkoutRepository workoutRepository){
        return args ->{
            Workout workout1 = new Workout();
            workout1.setExternalWorkoutId(UUID.randomUUID());
            workout1.setWorkoutName("Chest Day");
            workout1.setWorkoutType(WorkoutType.STRENGTH);
            workout1.setWorkoutDateTime(LocalDateTime.now());

            workoutRepository.save(workout1);

            System.out.println("Sample H2 data initialized");
        };
    }
}
