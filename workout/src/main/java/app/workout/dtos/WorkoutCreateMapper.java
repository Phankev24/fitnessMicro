package app.workout.dtos;

import app.workout.workout.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutCreateMapper{
    public WorkoutCreateDto toDTO(Workout workout){
        if(workout == null) return null;

        return new WorkoutCreateDto(
                workout.getUserId(),
                workout.getWorkoutName(),
                workout.getWorkoutType()
        );
    }

    public Workout toEntity(WorkoutCreateDto workoutCreateDto){
        if(workoutCreateDto == null) return null;

        Workout workout = new Workout();
        workout.setUserId(workoutCreateDto.userId());
        workout.setWorkoutName(workoutCreateDto.workoutName());
        workout.setWorkoutType(workoutCreateDto.workoutType());

        return workout;
    }

}
