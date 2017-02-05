package com.stolser.controller;

public class VideoImporterImpl implements VideoImporter {
    @Override
    public ProcessImportResult importVideo(SearchParameters params) {



         return new ProcessImportResult(true, ProcessImportResult.RESULT_SUCCESS, 11);
    }

}
