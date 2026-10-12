package app.workout.dtos.workout;

import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.entities.Workout;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WorkoutResponseMapper {
    public WorkoutResponseDto toDTO(Workout workout){
        if(workout == null) return null;

        List<WorkoutExerciseResponseDto> exerciseDtos = workout.getWorkoutExercises()
                .stream()
                .map(we -> new WorkoutExerciseResponseDto(
                        we.getWorkoutExerciseId(),
                        we.getExerciseName()
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
