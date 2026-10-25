package app.workout.dtos.exercise;

import app.workout.entities.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseResponseMapper {
    public ExerciseResponseDto toDTO(Exercise exercise) {
        if (exercise == null) return null;

        return new ExerciseResponseDto(
                exercise.getWorkoutExercise().getExternalWorkoutExerciseId(),
                exercise.getExerciseId(),
                exercise.getReps(),
                exercise.getWeight()
        );
    }
}

