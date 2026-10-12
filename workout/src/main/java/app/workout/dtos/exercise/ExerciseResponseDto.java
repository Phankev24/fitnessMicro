package app.workout.dtos.exercise;

public record ExerciseResponseDto(
        Long exerciseId,
        Integer reps,
        Double weight

) {
}
