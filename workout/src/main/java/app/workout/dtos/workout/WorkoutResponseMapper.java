package app.workout.dtos.workout;

import app.workout.dtos.exercise.ExerciseResponseDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.entities.Workout;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkoutResponseMapper {
    public WorkoutResponseDto toDTO(Workout workout) {
        if (workout == null) return null;

        List<WorkoutExerciseResponseDto> exerciseDtos = workout.getWorkoutSession()
                .stream()
                .map(ws -> new WorkoutExerciseResponseDto(
                        ws.getWorkoutExerciseId(),
                        ws.getWorkout().getExternalWorkoutId(), 
                        ws.getExerciseName(),
                        ws.getSets()
                                .stream()
                                .map(s -> new ExerciseResponseDto(
                                        s.getWorkoutExercise() != null ? s.getWorkoutExercise().getWorkoutExerciseId() : null,
                                        s.getReps(),
                                        s.getWeight()
                                ))
                                .toList()
                ))
                .toList();

        return new WorkoutResponseDto(
                workout.getInternalWorkoutId(),
                workout.getExternalWorkoutId(),
                workout.getUserId(),
                workout.getWorkoutName(),
                workout.getWorkoutDescription(),
                workout.getWorkoutDateTime(),
                workout.getWorkoutType(),
                exerciseDtos
        );
    }
}
