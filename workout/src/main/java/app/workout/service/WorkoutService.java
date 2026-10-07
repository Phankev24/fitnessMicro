package app.workout.service;

import app.workout.dtos.*;
import app.workout.entities.Workout;
import app.workout.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutResponseMapper workoutResponseMapper;
    private final WorkoutCreateMapper workoutCreateMapper;
    private final WorkoutUpdateMapper workoutUpdateMapper;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutResponseMapper workoutResponseMapper, WorkoutCreateMapper workoutCreateMapper, WorkoutUpdateMapper workoutUpdateMapper){
        this.workoutRepository = workoutRepository;
        this.workoutResponseMapper = workoutResponseMapper;
        this.workoutCreateMapper = workoutCreateMapper;
        this.workoutUpdateMapper = workoutUpdateMapper;
    }

    public List<WorkoutResponseDto> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutResponseMapper::toDTO)
                .toList();
    }

    public WorkoutResponseDto getWorkoutById(Long workoutId){
        return workoutRepository.findById(workoutId)
                .map(workoutResponseMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Workout not found with workoutId: " + workoutId));
    }

    public WorkoutResponseDto createWorkout(WorkoutCreateDto workoutCreateDto){
        Workout workoutEntity = workoutCreateMapper.toEntity(workoutCreateDto);
        Workout savedWorkout = workoutRepository.save(workoutEntity);
        return workoutResponseMapper.toDTO(savedWorkout);
    }

    public WorkoutResponseDto updateWorkout(Long id, WorkoutUpdateDto workoutUpdateDto) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found with this id: " + id));
        workoutUpdateMapper.updateEntityFromDto(workoutUpdateDto, workout);
        Workout updatedWorkout = workoutRepository.save(workout);
        return workoutResponseMapper.toDTO(updatedWorkout);
    }

    public void deleteWorkout(Long workoutId){
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout not found: " + workoutId));
        workoutRepository.delete(workout);
    }

    public List<WorkoutResponseDto> getWorkoutByUserId(UUID userId){
        return workoutRepository.findByUserId(userId)
                .stream()
                .map(workoutResponseMapper::toDTO)
                .toList();
    }
}
