package app.workout.dtos;

import app.workout.workout.Workout;
import org.springframework.stereotype.Component;


@Component
public class WorkoutResponseMapper {
    public WorkoutResponseDto toDTO(Workout workout){
        if(workout == null) return null;

        return new WorkoutResponseDto(
                workout.getInternalWorkoutId(),
                workout.getExternalWorkoutId(),
                workout.getUserId(),
                workout.getWorkoutName(),
                workout.getWorkoutDateTime(),
                workout.getWorkoutType()
        );
    }
}
