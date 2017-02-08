package com.stolser.entity;

import com.stolser.search.SingleVideoResult;

public class SeriesCreator extends BasicVideoCreator {
    @Override
    public Video create(SingleVideoResult json) {
        Series series = new Series();
        series.setTotalSeasons(parseIntField("TotalSeasons", json.getTotalSeasons()));

        return fillCommonVideoFields(series, json);
    }
}
