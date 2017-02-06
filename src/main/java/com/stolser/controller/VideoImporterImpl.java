package com.stolser.controller;

import java.util.Properties;

import static com.stolser.ApplicationResources.*;

public class VideoImporterImpl implements VideoImporter {
    @Override
    public ProcessImportResult importVideo(SearchParameters params) {
        System.out.println("starting the job...");
//        JobOperator jobOperator = BatchRuntime.getJobOperator();
//        jobOperator.start("importVideo", new Properties());
        System.out.println("... the job was completed.");

         return new ProcessImportResult(true, ProcessImportResult.RESULT_SUCCESS_MSG, 11);
    }

    private Properties getProperties(SearchParameters params) {
        Properties props = new Properties();
        props.setProperty(SEARCH_TEXT_PARAM, params.getSearchText());
        props.setProperty(SEARCH_YEAR_PARAM, String.valueOf(params.getSearchYear()));
        props.setProperty(SEARCH_VIDEO_TYPE_PARAM, params.getSearchVideoType().toString());
        return props;
    }

}
