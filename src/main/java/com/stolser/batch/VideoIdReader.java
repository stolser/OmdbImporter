package com.stolser.batch;

import com.stolser.controller.SearchParameters;
import com.stolser.entity.Video;
import com.stolser.search.IdSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

public class VideoIdReader implements ItemReader<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoIdProcessor.class);

    @Value("#{jobParameters['searchText']}")
    private String searchText;

    @Value("#{jobParameters['searchYear']}")
    private long searchYear;

    @Value("#{jobParameters['searchVideoType']}")
    private String searchVideoType;

    @Autowired
    private IdSearcher idSearcher;

    private List<String> imdbIds;

    @PostConstruct
    public void init() {
        SearchParameters searchParameters = getSearchParameters();
        imdbIds = idSearcher.searchImdbIds(searchParameters);

        LOGGER.debug(String.format("%d imdbIds have been found with %s", imdbIds.size(), searchParameters));
    }

    @Override
    public String read() throws Exception {
        if (!imdbIds.isEmpty()) {
            return imdbIds.remove(0);
        }

        return null;
    }

    private SearchParameters getSearchParameters() {
        return new SearchParameters(searchText, (int) searchYear,
                Video.Type.valueOf(searchVideoType.toUpperCase()));
    }
}
