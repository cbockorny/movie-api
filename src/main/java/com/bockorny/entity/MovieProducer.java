package com.bockorny.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="movie_producer")
public class MovieProducer {

	@Id
	@Column(name="id_movie_producer")
	private Long id;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private Producer producer;
	
	public MovieProducer() {}
	
	public MovieProducer(Movie movie, Producer producer) {
		this.id = movie.getId() + producer.getId();
		this.movie = movie;
		this.producer = producer;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MovieProducer that = (MovieProducer) o;
		return Objects.equals(movie, that.movie) && Objects.equals(producer, that.producer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, producer);
	}
}