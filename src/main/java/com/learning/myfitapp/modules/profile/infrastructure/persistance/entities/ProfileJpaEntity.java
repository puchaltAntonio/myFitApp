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
    public static final String USERNAME_COL = "username";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ID_COL)
    private UUID id;

    @Column(name = USERNAME_COL)
    private String username;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<WorkoutJpaEntity> workouts = new ArrayList<>();


}
