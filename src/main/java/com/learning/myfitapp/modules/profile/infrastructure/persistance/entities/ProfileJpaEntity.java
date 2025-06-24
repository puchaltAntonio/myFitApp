package com.learning.myfitapp.modules.profile.infrastructure.persistance.entities;

import com.learning.myfitapp.modules.workout.infrastructure.persistance.entities.WorkoutJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = ProfileJpaEntity.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileJpaEntity {

    public static final String TABLE_NAME = "profile";

    public static final String ID_COL = "id";
    public static final String NAME_COL = "name";
    public static final String SURNAME_COL = "surname";
    public static final String EMAIL_COL = "email";

    @Id
    @Column(name = ID_COL)
    private UUID id;

    @Column(name = NAME_COL, nullable = false)
    private String name;

    @Column(name = SURNAME_COL, nullable = false)
    private String surname;

    @Column(name = EMAIL_COL, nullable = false)
    private String email;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<WorkoutJpaEntity> workouts = new ArrayList<>();


}
