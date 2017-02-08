package com.stolser.controller;

import com.stolser.entity.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.stolser.ApplicationResources.*;
import static com.stolser.entity.Video.Type.MOVIE;
import static com.stolser.entity.Video.Type.SERIES;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = IMPORT_FORM_URI)
class ImportController {
    static final String YEAR_MIN_ATTR = "yearMin";
    static final String YEAR_MAX_ATTR = "yearMax";
    static final String VIDEO_TYPES_ATTR = "videoTypes";
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportController.class);
    static final String IMPORT_DATA_HAS_BEEN_STARTED_RESPONSE = "Import data has been started...";
    static final String VALIDATION_ERROR_RESPONSE = "Validation error!";

    @Autowired
    private Environment env;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        System.out.println("DataIntegrityViolationException is caught ---------------------------");
    }

    @RequestMapping(method = GET)
    public String importFormPage(Model model) {
        model.addAttribute(YEAR_MIN_ATTR, env.getRequiredProperty("search.yearMin"));
        model.addAttribute(YEAR_MAX_ATTR, env.getRequiredProperty("search.yearMax"));
        model.addAttribute(VIDEO_TYPES_ATTR, getVideoTypes());

        return IMPORT_FORM_VIEW_NAME;
    }

    private List<Video.Type> getVideoTypes() {
        List<Video.Type> types = new ArrayList<>();
        types.add(MOVIE);
        types.add(SERIES);

        return types;
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public String processImport(@Valid SearchParameters requestParams, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            LOGGER.debug(String.format("Validation errors occurred. Errors: %s", errors.getAllErrors()));
            return VALIDATION_ERROR_RESPONSE;
        }

        LOGGER.debug("SearchParameters = " + requestParams);

        try {
            jobLauncher.run(job, getJobParameters(requestParams));

            return IMPORT_DATA_HAS_BEEN_STARTED_RESPONSE;
        } catch (JobInstanceAlreadyCompleteException e) {
            return String.format("Importing videos with search parameters '%s' has already completed.",
                    requestParams);
        }
    }

    JobParameters getJobParameters(SearchParameters requestParams) {
        return new JobParametersBuilder()
                .addString(SEARCH_TEXT_PARAM, requestParams.getSearchText())
                .addLong(SEARCH_YEAR_PARAM, (long) requestParams.getSearchYear())
                .addString(SEARCH_VIDEO_TYPE_PARAM, getVideoType(requestParams))
                .toJobParameters();
    }

    private String getVideoType(SearchParameters requestParams) {
        return requestParams.getSearchVideoType().name();
    }
}
