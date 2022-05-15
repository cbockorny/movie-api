package com.bockorny.utils;

import com.bockorny.entity.Movie;
import com.bockorny.entity.MovieProducer;
import com.bockorny.entity.Producer;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static List<Pair<Movie, Set<Producer>>> extractMovies(InputStream is) throws IOException, CsvException {
        final InputStreamReader reader = new InputStreamReader(is);
        final CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        final CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();

        final List<Pair<Movie, Set<Producer>>> movies = new ArrayList<>();
        for (String[] row : csvReader.readAll()) {

            final Integer year = Integer.parseInt(row[0]);
            final String title = row[1];
            final String studio = row[2];
            final Set<Producer> producers = extractProducers(row[3]);
            final boolean winner = "yes".equalsIgnoreCase(row[4]);

            movies.add(Pair.of(new Movie(year, title, studio, winner), producers));
        }
        return movies;
    }

    public static Set<Producer> extractProducers(final String string){
        final String producers = string.replaceAll("and ", ",").replaceAll(" and ", ",");
        return Arrays.stream(producers.split(",")).filter(p -> p.trim().length() > 1).map(p -> new Producer(p.trim())).collect(Collectors.toSet());
    }
}