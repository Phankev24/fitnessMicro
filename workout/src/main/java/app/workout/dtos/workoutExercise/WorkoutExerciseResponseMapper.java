package app.workout.dtos.workoutExercise;

import app.workout.dtos.exercise.ExerciseResponseDto;
import app.workout.entities.WorkoutExercise;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkoutExerciseResponseMapper {
    public WorkoutExerciseResponseDto toDTO(WorkoutExercise workoutExercise) {
        if (workoutExercise == null) return null;

        List<ExerciseResponseDto> setDtos = workoutExercise.getSets()
                .stream()
                .map(s -> new ExerciseResponseDto(
                        s.getWorkoutExercise() != null ? s.getWorkoutExercise().getWorkoutExerciseId() : null,
                        s.getExerciseId(),
                        s.getReps(),
                        s.getWeight()
                ))
                .toList();

        return new WorkoutExerciseResponseDto(
                workoutExercise.getWorkoutExerciseId(),
                workoutExercise.getWorkout().getExternalWorkoutId(),
                workoutExercise.getExerciseName(),
                setDtos
        );
    }
}

