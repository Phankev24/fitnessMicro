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
                        s.getReps(),
                        s.getWeight()
                ))
                .toList();

//        Long internalWorkoutId = workoutExercise.getWorkout() != null
//                ? workoutExercise.getWorkout().getInternalWorkoutId()
//                : null;

        return new WorkoutExerciseResponseDto(
                workoutExercise.getWorkoutExerciseId(),
                workoutExercise.getExerciseName(),
                setDtos
        );
    }
}

