package com.bockorny.dto;

import com.bockorny.entity.Movie;

import java.util.stream.Collectors;

public class MovieDTO {

    private final int year;
    private final String title, studio, producers;
    private final boolean winner;

    public MovieDTO(final Movie movie) {
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.studio = movie.getStudio();
        this.producers = movie.getProducers().stream().map(p -> p.getProducer().getName()).collect(Collectors.joining(", "));
        this.winner = movie.isWinner();
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getStudio() {
        return studio;
    }

    public String getProducers() {
        return producers;
    }

    public boolean isWinner() {
        return winner;
    }
}
