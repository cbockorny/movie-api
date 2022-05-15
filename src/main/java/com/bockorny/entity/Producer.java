package com.bockorny.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="producer")
public class Producer {
	
	@Id
	@Column(name="id_producer")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length=50, nullable=false)
	private String name;
	
	@OneToMany(mappedBy="producer", cascade=CascadeType.ALL)
	private Set<MovieProducer> movies;

	public Producer() {}

	public Producer(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MovieProducer> getMovies() {
		return movies;
	}

	public void setMovies(Set<MovieProducer> movies) {
		this.movies = movies;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Producer producer = (Producer) o;
		return id.equals(producer.id) && name.equals(producer.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}