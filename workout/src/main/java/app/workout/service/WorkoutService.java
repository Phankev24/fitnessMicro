package app.workout.service;

import app.workout.dtos.*;
import app.workout.workout.Workout;
import app.workout.workout.WorkoutRepository;
import org.hibernate.jdbc.Work;
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

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutResponseMapper workoutResponseMapper, WorkoutCreateMapper workoutCreateMapper){
        this.workoutRepository = workoutRepository;
        this.workoutResponseMapper = workoutResponseMapper;
        this.workoutCreateMapper = workoutCreateMapper;
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
        if (workoutUpdateDto.workoutName() != null) {
            workout.setWorkoutName(workoutUpdateDto.workoutName());
        }

        if (workoutUpdateDto.workoutDescription() != null) {
            workout.setWorkoutDescription(workoutUpdateDto.workoutDescription());
        }

        if (workoutUpdateDto.workoutType() != null) {
            workout.setWorkoutType(workoutUpdateDto.workoutType());
        }

        if (workoutUpdateDto.workoutDateTime() != null) {
            workout.setWorkoutDateTime(workoutUpdateDto.workoutDateTime());
        }

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
