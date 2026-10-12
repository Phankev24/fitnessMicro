package app.workout.dtos.workoutExercise;

import app.workout.dtos.exercise.ExerciseResponseDto;

import java.util.List;

public record WorkoutExerciseResponseDto(
        String exerciseName,
        List<ExerciseResponseDto> sets
) {
}
