package app.workout.controller;

import app.workout.dtos.WorkoutResponseDto;
import app.workout.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getAllWorkout(){
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }
}
