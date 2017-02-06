package com.stolser.entity;

import com.stolser.search.SearchIdResult;

public class EpisodeCreator extends BasicVideoCreator {
    @Override
    public Video create(SearchIdResult json) {
        Episode episode = new Episode();
        episode.setEpisodeNumber(parseIntField("EpisodeNumber", json.getEpisodeNumber()));
        episode.setSeasonNumber(parseIntField("SeasonNumber", json.getSeasonNumber()));

        return fillCommonVideoFields(episode, json);
    }
}
