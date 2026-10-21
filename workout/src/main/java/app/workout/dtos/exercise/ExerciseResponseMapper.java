package app.workout.dtos.exercise;

import app.workout.entities.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseResponseMapper {
    public ExerciseResponseDto toDTO(Exercise exercise) {
        if (exercise == null) return null;

        Long workoutExerciseId = exercise.getWorkoutExercise() != null
                ? exercise.getWorkoutExercise().getWorkoutExerciseId()
                : null;

        return new ExerciseResponseDto(
                workoutExerciseId,
                exercise.getExerciseId(),
                exercise.getReps(),
                exercise.getWeight()
        );
    }
}

