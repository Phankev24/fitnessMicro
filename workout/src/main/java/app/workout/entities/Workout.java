package app.workout.entities;

import app.workout.enums.WorkoutType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internalWorkoutId;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID externalWorkoutId;

    @Column(nullable = false)
    private UUID userId;

    private String workoutName;
    private String workoutDescription;
    private LocalDateTime workoutDateTime;

    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();

    public Workout(WorkoutType workoutType, LocalDateTime workoutDateTime, String workoutDescription, String workoutName, UUID externalWorkoutId) {
        this.workoutType = workoutType;
        this.workoutDateTime = workoutDateTime;
        this.workoutDescription = workoutDescription;
        this.workoutName = workoutName;
        this.externalWorkoutId = externalWorkoutId;
    }

    @PrePersist
    public void generateUuid(){
        if(this.externalWorkoutId == null){
            this.externalWorkoutId = UUID.randomUUID();
        }
        if(this.workoutDateTime == null){
            this.workoutDateTime = LocalDateTime.now();
        }
    }
}
