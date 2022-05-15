package com.bockorny.dto;

import com.bockorny.entity.Producer;

import java.util.stream.Collectors;

public class ProducerDTO {

    private final String name, movies;

    public ProducerDTO(final String name, final String movies) {
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public String getMovies() {
        return movies;
    }
}
