package app.workout.dtos.workoutExercise;

import app.workout.entities.Workout;
import app.workout.entities.WorkoutExercise;
import app.workout.repository.WorkoutRepository;
import app.workout.service.WorkoutExerciseService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WorkoutExerciseCreateMapper {

    private final WorkoutRepository workoutRepository;

    public WorkoutExerciseCreateMapper(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public WorkoutExercise toEntity(WorkoutExerciseCreateDto workoutExerciseCreateDto, UUID externalWorkoutId){
        if(workoutExerciseCreateDto == null) return null;

        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setExerciseName(workoutExerciseCreateDto.exerciseName());

        // Find the workout by externalWorkoutId and set the relationship
        Workout workout = workoutRepository.findByExternalWorkoutId(externalWorkoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found with externalWorkoutId: " + externalWorkoutId));
        workoutExercise.setWorkout(workout);

        return workoutExercise;
    }
}
