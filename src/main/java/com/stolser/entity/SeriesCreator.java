package com.stolser.entity;

import com.stolser.search.SearchIdResult;

public class SeriesCreator extends BasicVideoCreator {
    @Override
    public Video create(SearchIdResult json) {
        Series series = new Series();
        series.setTotalSeasons(parseIntField("TotalSeasons", json.getTotalSeasons()));

        return fillCommonVideoFields(series, json);
    }
}
