
package app.workout.entities;

import app.workout.enums.WorkoutType;
import app.workout.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
@Profile("dev")
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(WorkoutRepository workoutRepository) {
        return args -> {

            // --- Workout 1: Upper Day ---
            Workout workout1 = new Workout();
            workout1.setExternalWorkoutId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
            workout1.setExternalUserId(UUID.fromString("9abd6f56-9a62-4f25-96c8-afbdf742aa0c"));
            workout1.setWorkoutName("Upper Day");
            workout1.setWorkoutDescription("Working on upper body today - specifically chest and back.");
            workout1.setWorkoutType(WorkoutType.STRENGTH);
            workout1.setWorkoutDateTime(LocalDateTime.now());

            // --- Workout 2: Leg Day ---
            Workout workout2 = new Workout();
            workout2.setExternalWorkoutId(UUID.fromString("4fa85f64-5717-4562-b3fc-2c963f66afa6"));
            workout2.setExternalUserId(UUID.fromString("92d82076-01a3-41ae-89ed-7bc138bde54f"));
            workout2.setWorkoutName("Leg Day");
            workout2.setWorkoutDescription("Working out the legs - focus on the quads.");
            workout2.setWorkoutType(WorkoutType.STRENGTH);
            workout2.setWorkoutDateTime(LocalDateTime.now());

            WorkoutExercise legPress = new WorkoutExercise();
            legPress.setExternalWorkoutExerciseId(UUID.fromString("3feb84f2-976e-4075-b244-b448eadab17c"));
            legPress.setExerciseName("Leg Press");
            legPress.setWorkout(workout2);

            workout2.getWorkoutSession().add(legPress);

            // --- Workout 3: Shoulders ---
            Workout workout3 = new Workout();
            workout3.setExternalWorkoutId(UUID.fromString("5f3b7d66-39c7-471a-8017-31635972f1a6"));
            workout3.setExternalUserId(UUID.fromString("82df388a-a1cd-47c0-9e6d-78d5b6bbbf7d"));
            workout3.setWorkoutName("Shoulders");
            workout3.setWorkoutDescription("Working on them shoulders.");
            workout3.setWorkoutType(WorkoutType.STRENGTH);
            workout3.setWorkoutDateTime(LocalDateTime.now());

            WorkoutExercise shoulderPress = new WorkoutExercise();
            shoulderPress.setExternalWorkoutExerciseId(UUID.fromString("c00e91a1-6b30-4e10-9e21-f4a91b84e334"));
            shoulderPress.setExerciseName("Shoulder Press");
            shoulderPress.setWorkout(workout3);

            // Attach Shoulder Press to Shoulders, not Leg Day
            workout3.getWorkoutSession().add(shoulderPress);

            // --- Shoulder Press Sets ---
            Exercise set1 = new Exercise(25.0, 10);
            set1.setWorkoutExercise(shoulderPress);
            shoulderPress.getSets().add(set1);

            Exercise set2 = new Exercise(25.0, 10);
            set2.setWorkoutExercise(shoulderPress);
            shoulderPress.getSets().add(set2);

            workoutRepository.save(workout1);
            workoutRepository.save(workout2);
            workoutRepository.save(workout3);

            System.out.println("Sample workout H2 data initialized!");
        };
    }
}
