package app.workout.workout;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    private String workoutName;
    private LocalDateTime workoutDateTime;

    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    public Workout(WorkoutType workoutType, LocalDateTime workoutDateTime, String workoutName, UUID externalWorkoutId) {
        this.workoutType = workoutType;
        this.workoutDateTime = workoutDateTime;
        this.workoutName = workoutName;
        this.externalWorkoutId = externalWorkoutId;
    }

    @PrePersist
    public void generateUuid(){
        if(this.externalWorkoutId == null){
            this.externalWorkoutId = UUID.randomUUID();
        }
    }
}
