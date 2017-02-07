package com.stolser.search;

import com.stolser.controller.SearchParameters;
import com.stolser.entity.Series;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

final class SearchUriProvider {
    private static final String BASIC_OMDBAPI_URL = "http://www.omdbapi.com/";
    private static final String SEARCH_PARAM = "s";
    private static final String TYPE_PARAM = "type";
    private static final String YEAR_PARAM = "y";
    private static final String ID_PARAM = "i";
    private static final String PLOT_PARAM = "plot";
    private static final String TOMATOES_PARAM = "tomatoes";
    private static final String FULL_VALUE = "full";
    private static final String TRUE_VALUE = "true";
    private static final String SEASON_PARAM = "Season";

    private SearchUriProvider() {}

    static URI getUriToSearchByParams(SearchParameters params) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASIC_OMDBAPI_URL)
                .queryParam(SEARCH_PARAM, params.getSearchText())
                .queryParam(TYPE_PARAM, params.getSearchVideoType().toString().toLowerCase());

        addYearForSearch(params, uriBuilder);

        return uriBuilder.build().toUri();
    }

    static URI getUriToSearchByImdbId(String imdbId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASIC_OMDBAPI_URL)
                .queryParam(ID_PARAM, imdbId)
                .queryParam(PLOT_PARAM, FULL_VALUE)
                .queryParam(TOMATOES_PARAM, TRUE_VALUE);

        return uriBuilder.build().toUri();
    }

    static URI getUriToSearchEpisodesBySeasonNumber(Series series, int seasonNumber) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASIC_OMDBAPI_URL)
                .queryParam(ID_PARAM, series.getImdbId())
                .queryParam(SEASON_PARAM, seasonNumber);

        return uriBuilder.build().toUri();
    }

    private static void addYearForSearch(SearchParameters params, UriComponentsBuilder uriBuilder) {
        int yearParam = params.getSearchYear();
        if (yearParam != 0) {
            uriBuilder.queryParam(YEAR_PARAM, yearParam);
        }
    }
}
