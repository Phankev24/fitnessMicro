package app.workout.service;

import app.workout.dtos.workoutExercise.WorkoutExerciseCreateDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseCreateMapper;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.dtos.workoutExercise.WorkoutExerciseResponseMapper;
import app.workout.entities.WorkoutExercise;
import app.workout.repository.WorkoutExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutExerciseService {
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutExerciseResponseMapper workoutExerciseResponseMapper;
    private final WorkoutExerciseCreateMapper workoutExerciseCreateMapper;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository, WorkoutExerciseResponseMapper workoutExerciseResponseMapper, WorkoutExerciseCreateMapper workoutExerciseCreateMapper) {
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutExerciseResponseMapper = workoutExerciseResponseMapper;
        this.workoutExerciseCreateMapper = workoutExerciseCreateMapper;
    }

    public List<WorkoutExerciseResponseDto> getAllWorkoutSessions(){
        return workoutExerciseRepository.findAll()
                .stream()
                .map(workoutExerciseResponseMapper::toDTO)
                .toList();
    }

    public WorkoutExerciseResponseDto createWorkoutSession(UUID externalWorkoutId, WorkoutExerciseCreateDto workoutExerciseCreateDto){
        WorkoutExercise workoutExerciseEntity = workoutExerciseCreateMapper.toEntity(externalWorkoutId ,workoutExerciseCreateDto);
        WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExerciseEntity);
        return workoutExerciseResponseMapper.toDTO(savedWorkoutExercise);
    }

    public void deleteWorkoutExercise(Long internalWorkoutExerciseId){
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(internalWorkoutExerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        workoutExerciseRepository.delete(workoutExercise);
    }
}
