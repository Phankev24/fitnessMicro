package app.workout.dtos;

import app.workout.entities.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutCreateMapper{
    public WorkoutCreateDto toDTO(Workout workout){
        if(workout == null) return null;

        return new WorkoutCreateDto(
                workout.getUserId(),
                workout.getWorkoutName(),
                workout.getWorkoutDescription(),
                workout.getWorkoutType()
        );
    }

    public Workout toEntity(WorkoutCreateDto workoutCreateDto){
        if(workoutCreateDto == null) return null;

        Workout workout = new Workout();
        workout.setUserId(workoutCreateDto.userId());
        workout.setWorkoutName(workoutCreateDto.workoutName());
        workout.setWorkoutDescription(workoutCreateDto.workoutDescription());
        workout.setWorkoutType(workoutCreateDto.workoutType());

        return workout;
    }

}
