package com.bockorny.repository;

import com.bockorny.entity.Movie;
import com.bockorny.entity.MovieProducer;
import com.bockorny.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {

    @Query(value="SELECT mp "
            + "FROM MovieProducer mp "
            + "JOIN Movie m ON (m.id = mp.movie.id)"
            + "WHERE m.winner = true "
            )
    List<MovieProducer> findWinnerMovies();
}