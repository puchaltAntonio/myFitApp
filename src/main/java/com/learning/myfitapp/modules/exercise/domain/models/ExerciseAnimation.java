package com.learning.myfitapp.modules.exercise.domain.models;

import java.net.URL;

public class ExerciseAnimation {

    private URL url;

    private String path;

    public ExerciseAnimation(
            final String path
    ) {
        this.path = path;
    }

    public void setUrl(final URL url){ this.url = url; }

    public void setPath(final String path){ this.path = path; }
}
