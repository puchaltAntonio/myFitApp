CREATE TABLE workout_session (
    id UUID PRIMARY KEY,
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    profile_id UUID NOT NULL,
    CONSTRAINT fk_workout_session_workout FOREIGN KEY (profile_id) REFERENCES workout(id) ON DELETE CASCADE
);