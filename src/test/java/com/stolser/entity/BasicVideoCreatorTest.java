package com.stolser.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stolser.search.SingleVideoResult;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static com.stolser.entity.Video.Genre.COMEDY;
import static com.stolser.entity.Video.Genre.ROMANCE;
import static com.stolser.entity.Video.Type.SERIES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BasicVideoCreatorTest {

    private Runtime expected;

    @Before
    public void setUp() throws Exception {
        expected = new Runtime();

    }

    @Test
    public void parseRuntime_10min_ShouldReturn_10min() throws Exception {
        String runtimeStr = "10 min";
        expected.setOriginal(runtimeStr);
        expected.setMinutes(10);

        Runtime actual = new BasicVideoCreator().parseRuntime(runtimeStr);

        assertEquals(expected, actual);
    }

    @Test
    public void parseRuntime_100min_ShouldReturn_100min() throws Exception {
        String runtimeStr = "100 min";
        expected.setOriginal(runtimeStr);
        expected.setMinutes(100);

        Runtime actual = new BasicVideoCreator().parseRuntime(runtimeStr);

        assertEquals(expected, actual);
    }

    @Test
    public void parseRuntime_3h_20min_ShouldReturn_200min() throws Exception {
        String runtimeStr = "3 h 20 min";
        expected.setOriginal(runtimeStr);
        expected.setMinutes(200);

        Runtime actual = new BasicVideoCreator().parseRuntime(runtimeStr);

        assertEquals(expected, actual);
    }

    @Test
    public void parseRuntime_N_A_ShouldReturn_null() throws Exception {
        String runtimeStr = "N/A";
        Runtime actual = new BasicVideoCreator().parseRuntime(runtimeStr);

        assertNull(actual);
    }

    @Test
    public void fillCommonVideoFields_Should_CorrectlyParse_SingleVideoJsonResult_IntoVideo() throws Exception {
        SingleVideoResult singleVideoResult = new ObjectMapper().readValue("{\n" +
                "  \"Title\": \"The Big Bang Theory\",\n" +
                "  \"Year\": \"2007–\",\n" +
                "  \"Rated\": \"TV-14\",\n" +
                "  \"Released\": \"01 Jan 2006\",\n" +
                "  \"Runtime\": \"22 min\",\n" +
                "  \"Genre\": \"Comedy, Romance\",\n" +
                "  \"Director\": \"N/A\",\n" +
                "  \"Writer\": \"Chuck Lorre, Bill Prady\",\n" +
                "  \"Actors\": \"Johnny Galecki, Jim Parsons, Kaley Cuoco, Simon Helberg\",\n" +
                "  \"Plot\": \"Leonard Hofstadter and Sheldon Cooper are both brilliant physicists working at Cal Tech in Pasadena, California.\",\n" +
                "  \"Language\": \"English, Hindi, Klingon\",\n" +
                "  \"Country\": \"USA\",\n" +
                "  \"Awards\": \"Won 1 Golden Globe. Another 60 wins & 211 nominations.\",\n" +
                "  \"Poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUyNDMxNj.jpg\",\n" +
                "  \"Metascore\": \"N/A\",\n" +
                "  \"imdbRating\": \"8.3\",\n" +
                "  \"imdbVotes\": \"551,410\",\n" +
                "  \"imdbID\": \"tt0898266\",\n" +
                "  \"Type\": \"series\",\n" +
                "  \"totalSeasons\": \"10\",\n" +
                "  \"tomatoMeter\": \"N/A\",\n" +
                "  \"tomatoImage\": \"N/A\",\n" +
                "  \"tomatoRating\": \"84\",\n" +
                "  \"tomatoReviews\": \"N/A\",\n" +
                "  \"tomatoFresh\": \"N/A\",\n" +
                "  \"tomatoRotten\": \"N/A\",\n" +
                "  \"tomatoConsensus\": \"N/A\",\n" +
                "  \"tomatoUserMeter\": \"94\",\n" +
                "  \"tomatoUserRating\": \"N/A\",\n" +
                "  \"tomatoUserReviews\": \"N/A\",\n" +
                "  \"tomatoURL\": \"N/A\",\n" +
                "  \"DVD\": \"18 Oct 2008\",\n" +
                "  \"BoxOffice\": \"N/A\",\n" +
                "  \"Production\": \"N/A\",\n" +
                "  \"Website\": \"N/A\",\n" +
                "  \"Response\": \"True\"\n" +
                "}", SingleVideoResult.class);

        Video actual = VideoCreators.newSeriesCreator().create(singleVideoResult);
        Series expected = new Series();
        fillExpectedFields(expected);

        assertEquals(expected, actual);
    }

    private void fillExpectedFields(Series expected) throws ParseException {
        expected.setTitle("The Big Bang Theory");
        setExpectedYear(expected);
        expected.setMpaaRating(Video.MpaaRating.TV_14);
        expected.setReleaseDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2006"));
        expected.setRuntime(new Runtime("22 min", 22));
        expected.setGenres(Arrays.asList(COMEDY, ROMANCE));
        expected.setDirectors(new ArrayList<>());
        expected.setWriters(Arrays.asList("Chuck Lorre", "Bill Prady"));
        expected.setActors(Arrays.asList("Johnny Galecki", "Jim Parsons", "Kaley Cuoco", "Simon Helberg"));
        expected.setPlot("Leonard Hofstadter and Sheldon Cooper are both brilliant physicists working at Cal Tech in Pasadena, California.");
        expected.setLanguages(Arrays.asList("English", "Hindi", "Klingon"));
        expected.setCountries(Arrays.asList("USA"));
        expected.setAwards("Won 1 Golden Globe. Another 60 wins & 211 nominations.");
        expected.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUyNDMxNj.jpg");
        expected.setMetascore(-1);
        expected.setImdbRating(8.3);
        expected.setImdbVotes(551410);
        expected.setImdbId("tt0898266");
        expected.setType(SERIES);
        expected.setTotalSeasons(10);
        setExpectedTomatoes(expected);
    }

    private void setExpectedTomatoes(Series expected) {
        TomatoesRating tomatoes = new TomatoesRating();
        tomatoes.setTomatoRating("84");
        tomatoes.setTomatoMeter("N/A");
        tomatoes.setTomatoImage("N/A");
        tomatoes.setTomatoRotten("N/A");
        tomatoes.setTomatoFresh("N/A");
        tomatoes.setTomatoReviews("N/A");
        tomatoes.setTomatoUserRating("N/A");
        tomatoes.setTomatoUserMeter("94");
        tomatoes.setTomatoConsensus("N/A");
        tomatoes.setDvdRelease("18 Oct 2008");
        tomatoes.setTomatoUrl("N/A");
        tomatoes.setTomatoUserReviews("N/A");
        tomatoes.setWebsite("N/A");
        tomatoes.setProduction("N/A");
        tomatoes.setBoxOffice("N/A");
        tomatoes.setVideo(expected);

        expected.setTomatoesRating(tomatoes);
    }

    private void setExpectedYear(Video expected) {
        Year year = new Year();
        year.setType(Year.Type.RANGE);
        year.setFinished(false);
        year.setBegin("2007");
        year.setEnd(null);
        expected.setYear(year);
    }
}