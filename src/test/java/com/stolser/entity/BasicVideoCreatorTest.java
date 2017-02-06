package com.stolser.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stolser.search.SearchIdResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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

    @Ignore
    @Test
    public void fillCommonVideoFields_CorrectlyParseJsonIntoVideo() throws Exception {
        SearchIdResult searchIdResult = new ObjectMapper().readValue("{\n" +
                "  \"Title\": \"The Big Bang Theory\",\n" +
                "  \"Year\": \"2007–\",\n" +
                "  \"Rated\": \"TV-14\",\n" +
                "  \"Released\": \"01 Jan 2006\",\n" +
                "  \"Runtime\": \"22 min\",\n" +
                "  \"Genre\": \"Comedy, Romance\",\n" +
                "  \"Director\": \"N/A\",\n" +
                "  \"Writer\": \"Chuck Lorre, Bill Prady\",\n" +
                "  \"Actors\": \"Johnny Galecki, Jim Parsons, Kaley Cuoco, Simon Helberg\",\n" +
                "  \"Plot\": \"Leonard Hofstadter and Sheldon Cooper are both brilliant physicists working at Cal Tech in Pasadena, California. They are colleagues, best friends, and roommates, although in all capacities their relationship is always tested primarily by Sheldon's regimented, deeply eccentric, and non-conventional ways. They are also friends with their Cal Tech colleagues mechanical engineer Howard Wolowitz and astrophysicist Rajesh Koothrappali. The foursome spend their time working on their individual work projects, playing video games, watching science-fiction movies, or reading comic books. As they are self-professed nerds, all have little or no luck with women. When Penny, a pretty woman and an aspiring actress from Omaha, moves into the apartment across the hall from Leonard and Sheldon's, Leonard has another aspiration in life, namely to get Penny to be his girlfriend.\",\n" +
                "  \"Language\": \"English, Hindi, Italian, Russian, Mandarin, Klingon\",\n" +
                "  \"Country\": \"USA\",\n" +
                "  \"Awards\": \"Won 1 Golden Globe. Another 60 wins & 211 nominations.\",\n" +
                "  \"Poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUyNDMxNjQyN15BMl5BanBnXkFtZTgwNzA4NDQwMDI@._V1_SX300.jpg\",\n" +
                "  \"Metascore\": \"N/A\",\n" +
                "  \"imdbRating\": \"8.3\",\n" +
                "  \"imdbVotes\": \"551,410\",\n" +
                "  \"imdbID\": \"tt0898266\",\n" +
                "  \"Type\": \"series\",\n" +
                "  \"totalSeasons\": \"10\",\n" +
                "  \"tomatoMeter\": \"N/A\",\n" +
                "  \"tomatoImage\": \"N/A\",\n" +
                "  \"tomatoRating\": \"N/A\",\n" +
                "  \"tomatoReviews\": \"N/A\",\n" +
                "  \"tomatoFresh\": \"N/A\",\n" +
                "  \"tomatoRotten\": \"N/A\",\n" +
                "  \"tomatoConsensus\": \"N/A\",\n" +
                "  \"tomatoUserMeter\": \"N/A\",\n" +
                "  \"tomatoUserRating\": \"N/A\",\n" +
                "  \"tomatoUserReviews\": \"N/A\",\n" +
                "  \"tomatoURL\": \"N/A\",\n" +
                "  \"DVD\": \"N/A\",\n" +
                "  \"BoxOffice\": \"N/A\",\n" +
                "  \"Production\": \"N/A\",\n" +
                "  \"Website\": \"N/A\",\n" +
                "  \"Response\": \"True\"\n" +
                "}", SearchIdResult.class);

        Video expected = VideoCreators.newSeriesCreator().create(searchIdResult);

    }
}