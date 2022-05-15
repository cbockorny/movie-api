package com.bockorny.utils;

import com.bockorny.entity.Movie;
import com.bockorny.entity.Producer;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UtilsTest {

    @Test
    public void extractMovie() throws IOException, CsvException {
        try (final InputStream is = getClass().getClassLoader().getResourceAsStream("movies-test.csv")) {
            final List<Pair<Movie, Set<Producer>>> movies = Utils.extractMovies(is);
            Assert.assertEquals(3, movies.size());
        }
    }

    @Test
    public void extractProducersWithOnlyOneProducer(){
        final Set<Producer> producers = Utils.extractProducers("Allan Carr");
        Assert.assertEquals(1, producers.size());
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Allan Carr")));
    }

    @Test
    public void extractProducersWithThreeProducersAnd(){
        final Set<Producer> producers = Utils.extractProducers("Ozzie Areu, Will Areu, and Mark E. Swinton");
        Assert.assertEquals(3, producers.size());
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Ozzie Areu")));
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Will Areu")));
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Mark E. Swinton")));
    }

    @Test
    public void extractProducersWithFourProducersAndComma(){
        final Set<Producer> producers = Utils.extractProducers("Avi Lerner, Kevin King Templeton, Yariv Lerner, and Les Weldon");
        Assert.assertEquals(4, producers.size());
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Avi Lerner")));
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Kevin King Templeton")));
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Yariv Lerner")));
        Assert.assertTrue(producers.stream().anyMatch(i -> i.getName().equalsIgnoreCase("Les Weldon")));
    }
}