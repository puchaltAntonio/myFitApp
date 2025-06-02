CREATE TABLE workout (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    profile_id UUID NOT NULL,
    CONSTRAINT fk_workout_profile FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE
);