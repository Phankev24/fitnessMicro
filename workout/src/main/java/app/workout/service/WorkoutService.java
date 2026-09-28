package app.workout.service;

import app.workout.dtos.WorkoutResponseDto;
import app.workout.dtos.WorkoutResponseMapper;
import app.workout.workout.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutResponseMapper workoutResponseMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutResponseMapper workoutResponseMapper){
        this.workoutRepository = workoutRepository;
        this.workoutResponseMapper = workoutResponseMapper;
    }

    public List<WorkoutResponseDto> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutResponseMapper::toDTO)
                .toList();
    }
}
