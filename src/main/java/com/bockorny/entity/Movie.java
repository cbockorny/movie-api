package com.bockorny.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="movie")
public class Movie {

    @Id
    @Column(name="id_movie")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="year_movie", nullable=false)
    private int year;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="studio", nullable=false)
    private String studio;

    @OneToMany(mappedBy="movie", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<MovieProducer> producers;
    @Column(name="winner", nullable=false)
    private boolean winner;

    public Movie() {}

    public Movie(final Integer year, final String title, final String studio, final boolean winner){
        this.year = year;
        this.title = title;
        this.studio = studio;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Set<MovieProducer> getProducers() {
        return producers;
    }

    public void setProducers(Set<MovieProducer> producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && id.equals(movie.id) && title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, title);
    }
}
