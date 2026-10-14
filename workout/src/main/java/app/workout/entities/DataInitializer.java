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
            workout1.setExternalWorkoutId(UUID.randomUUID());
            workout1.setUserId(UUID.randomUUID());
            workout1.setWorkoutName("Upper Day");
            workout1.setWorkoutDescription("Working on upper body today - specifically chest and back.");
            workout1.setWorkoutType(WorkoutType.STRENGTH);
            workout1.setWorkoutDateTime(LocalDateTime.now());

            // Exercise 1: Bench Press
            WorkoutExercise workoutExercise1 = new WorkoutExercise();
            workoutExercise1.setExerciseName("Bench Press");
            workoutExercise1.setWorkout(workout1);
            workout1.getWorkoutSession().add(workoutExercise1);

            Exercise set1 = new Exercise(70.0, 10);
            set1.setWorkoutExercise(workoutExercise1);
            workoutExercise1.getSets().add(set1);

            // Exercise 2: Lat Pulldown
            WorkoutExercise workoutExercise3 = new WorkoutExercise();
            workoutExercise3.setExerciseName("Lat Pulldown");
            workoutExercise3.setWorkout(workout1);
            workout1.getWorkoutSession().add(workoutExercise3);

            // --- Workout 2: Leg Day ---
            Workout workout2 = new Workout();
            workout2.setExternalWorkoutId(UUID.randomUUID());
            workout2.setUserId(UUID.randomUUID());
            workout2.setWorkoutName("Leg Day");
            workout2.setWorkoutDescription("Working out the legs - focus on the quads.");
            workout2.setWorkoutType(WorkoutType.STRENGTH);
            workout2.setWorkoutDateTime(LocalDateTime.now());

            WorkoutExercise workoutExercise2 = new WorkoutExercise();
            workoutExercise2.setExerciseName("Leg Press");
            workoutExercise2.setWorkout(workout2);
            workout2.getWorkoutSession().add(workoutExercise2);

            workoutRepository.save(workout1);
            workoutRepository.save(workout2);

            System.out.println("Sample H2 data initialized");
        };
    }
}
