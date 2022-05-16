package com.bockorny.service;

import com.bockorny.dto.ProducerDTO;
import com.bockorny.dto.ProducerIntervalDTO;
import com.bockorny.dto.ProducerMinMaxDTO;
import com.bockorny.entity.MovieProducer;
import com.bockorny.entity.Producer;
import com.bockorny.repository.MovieProducerRepository;
import com.bockorny.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    @Autowired
    private MovieProducerRepository movieProducerRepository;

    public List<ProducerDTO> findAll() {
        final List<ProducerDTO> producerDto = new ArrayList<>();
        final Map<String, List<MovieProducer>> producersMovie = movieProducerRepository.findAll().stream().collect(Collectors.groupingBy(i -> i.getProducer().getName()));
        for (Map.Entry<String, List<MovieProducer>> entry : producersMovie.entrySet()) {
            final String movies = entry.getValue().stream().map(i -> String.format("[%s] %s", i.getMovie().getYear(), i.getMovie().getTitle())).collect(Collectors.joining(","));
            producerDto.add(new ProducerDTO(entry.getKey(), movies));
        }
        return producerDto;
    }

    public List<ProducerDTO> findWithWinnerMovie() {
        final List<ProducerDTO> producerDto = new ArrayList<>();
        final Map<String, List<MovieProducer>> producersMovie = movieProducerRepository.findAll().stream().collect(Collectors.groupingBy(i -> i.getProducer().getName()));
        for (Map.Entry<String, List<MovieProducer>> entry : producersMovie.entrySet()) {
            final String movies = entry.getValue().stream().filter(m -> m.getMovie().isWinner()).map(i -> String.format("[%s] %s", i.getMovie().getYear(), i.getMovie().getTitle())).collect(Collectors.joining(","));
            if (!movies.isEmpty()){
                producerDto.add(new ProducerDTO(entry.getKey(), movies));
            }
        }
        return producerDto;
    }

    public ProducerMinMaxDTO findMinMaxWinner() {
        final List<MovieProducer> producers = movieProducerRepository.findWinnerMovies();
        if (!producers.isEmpty()) {

            final Map<Integer, List<ProducerIntervalDTO>> mapInterval = new HashMap<>();
            final Map<String, List<MovieProducer>> mapByProducer = producers.stream().collect(Collectors.groupingBy(o -> o.getProducer().getName()));
            for (Map.Entry<String, List<MovieProducer>> entry : mapByProducer.entrySet()) {

                final String producer = entry.getKey();
                final List<MovieProducer> movies = entry.getValue();
                if (movies.size() > 1) {
                    final List<Integer> years = movies.stream().map(i -> i.getMovie().getYear()).sorted().toList();

                    for (int i = 0; i < years.size()-1; i++) {
                        final int firstYear = years.get(i);
                        final int secondYear = years.get(i+1);
                        final int diff = secondYear - firstYear;

                        mapInterval.putIfAbsent(diff, new ArrayList<>());
                        mapInterval.get(diff).add(new ProducerIntervalDTO(producer, diff, firstYear, secondYear));
                    }
                }
            }

            if (!mapInterval.isEmpty()) {
                final List<Integer> keys = mapInterval.keySet().stream().sorted().toList();
                final int firstInterval = keys.get(0);
                final int lastInterval = keys.get(keys.size() - 1);

                final List<ProducerIntervalDTO> intervalMin = mapInterval.get(firstInterval);
                final List<ProducerIntervalDTO> intervalMax = mapInterval.get(lastInterval);
                return new ProducerMinMaxDTO(intervalMin, intervalMax);
            }
        }
        return null;
    }
}