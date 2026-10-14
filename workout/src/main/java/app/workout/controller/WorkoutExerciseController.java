package app.workout.controller;

import app.workout.dtos.workoutExercise.WorkoutExerciseResponseDto;
import app.workout.service.WorkoutExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workoutExercise")
public class WorkoutExerciseController {
    private final WorkoutExerciseService workoutExerciseService;
    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutExerciseResponseDto>> getAllWorkoutExercises(){
        return ResponseEntity.ok(workoutExerciseService.getAllWorkoutSessions());
    }

}
