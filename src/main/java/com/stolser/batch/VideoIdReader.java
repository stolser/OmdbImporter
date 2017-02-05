package com.stolser.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class VideoIdReader implements ItemReader<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoIdReader.class);

//    @Inject
//    @BatchProperty (name = SEARCH_TEXT_PARAM)
//    private String searchText;
//
//    @Inject
//    @BatchProperty (name = SEARCH_YEAR_PARAM)
//    private int searchYear;
//
//    @Inject
//    @BatchProperty (name = SEARCH_VIDEO_TYPE_PARAM)
//    private String searchVideoType;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        LOGGER.debug("searchText = " + searchText);
//        LOGGER.debug("searchYear = " + searchYear);
//        LOGGER.debug("searchVideoType = " + searchVideoType);

        return null;
    }
}
