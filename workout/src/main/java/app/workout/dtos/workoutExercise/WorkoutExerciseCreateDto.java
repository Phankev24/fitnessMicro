package app.workout.dtos.workoutExercise;

public record WorkoutExerciseCreateDto(
        Long internalWorkoutId,
        String exerciseName
) {}
