package app.workout.service;

import app.workout.dtos.workoutExercise.WorkoutExerciseCreateMapper;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseMapper;
import app.workout.repository.WorkoutExerciseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutExerciseService {
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutExerciseResponseMapper workoutExerciseResponseMapper;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository, WorkoutExerciseResponseMapper workoutExerciseResponseMapper) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutExerciseResponseMapper = workoutExerciseResponseMapper;
    }

    public List<WorkoutExerciseResponseDto> getAllWorkoutSessions(){
        return workoutExerciseRepository.findAll()
                .stream()
                .map(workoutExerciseResponseMapper::toDTO)
                .toList();
    }
}
