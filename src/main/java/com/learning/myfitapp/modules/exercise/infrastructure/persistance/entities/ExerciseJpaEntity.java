package com.learning.myfitapp.modules.exercise.infrastructure.persistance.entities;

import com.learning.myfitapp.modules.exercise.domain.models.MuscleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = ExerciseJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseJpaEntity {

    public static final String TABLE_NAME = "exercise";

    public static final String ID_COL = "id";
    public static final String NAME_COL = "name";
    public static final String PRIMARY_MUSCLE_COL = "primary_muscle";
    public static final String SECONDARY_MUSCLE_COL = "secondary_muscle";
    public static final String EQUIPMENT = "equipment";
    public static final String EXERCISE_ANIMATION = "exercise_animation";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COL)
    private UUID id;

    @Column(name = NAME_COL, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = PRIMARY_MUSCLE_COL, nullable = false)
    private MuscleEnum primaryMuscle;

    @Enumerated(EnumType.STRING)
    @Column(name = SECONDARY_MUSCLE_COL, nullable = false)
    private MuscleEnum secondaryMuscle;

    @Enumerated(EnumType.STRING)
    @Column(name = EQUIPMENT, nullable = false)
    private MuscleEnum equipment;

    @Column(name = EXERCISE_ANIMATION)
    private String animation;

}
