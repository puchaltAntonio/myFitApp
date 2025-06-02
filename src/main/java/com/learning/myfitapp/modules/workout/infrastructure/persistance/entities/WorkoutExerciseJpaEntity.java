package com.learning.myfitapp.modules.workout.infrastructure.persistance.entities;

import com.learning.myfitapp.modules.exercise.infrastructure.persistance.entities.ExerciseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = WorkoutExerciseJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExerciseJpaEntity {

    public static final String TABLE_NAME = "workout_exercise";
    public static final String ID_COL = "id";
    public static final String WORKOUT_ID_COL = "workout_id";
    public static final String EXERCISE_ID_COL = "exercise_id";
    public static final String ORDER_COL = "order";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COL)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = WORKOUT_ID_COL, nullable = false)
    private WorkoutJpaEntity workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EXERCISE_ID_COL, nullable = false)
    private ExerciseJpaEntity exercise;

    @Column(name = ORDER_COL, nullable = false)
    private int order;
}
