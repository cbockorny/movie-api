package com.bockorny.service;

import com.bockorny.dto.MovieDTO;
import com.bockorny.entity.Movie;
import com.bockorny.entity.MovieProducer;
import com.bockorny.entity.Producer;
import com.bockorny.repository.MovieProducerRepository;
import com.bockorny.repository.MovieRepository;
import com.bockorny.repository.ProducerRepository;
import com.bockorny.utils.Utils;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private MovieProducerRepository movieProducerRepository;

    public void importMoviesByInputStream(InputStream is) throws IOException, CsvException {
        final Logger logger = Logger.getLogger(MovieService.class.getName());
        logger.info("Start insert movies...");

        final List<Pair<Movie, Set<Producer>>> list = Utils.extractMovies(is);

        for (Pair<Movie, Set<Producer>> pair : list) {

            final Movie movie = pair.getLeft();
            final Set<Producer> producers = pair.getRight();

            final Movie movieSave = movieRepository.save(movie);

            for (Producer producer : producers) {
                Producer producerSave = producerRepository.findByName(producer.getName());
                if (producerSave == null) {
                    producerSave = producerRepository.save(producer);
                }
                movieProducerRepository.save(new MovieProducer(movieSave, producerSave));
            }
            logger.info("Movie insert: " + movieSave.getTitle());
        }
        logger.info("Finish insert movies...");
    }

    public List<MovieDTO> findAll() {
        final List<MovieDTO> moviesDto = new ArrayList<>();
        for (Movie m : movieRepository.findAll()) {
            moviesDto.add(new MovieDTO(m));
        }
        return moviesDto;
    }
}