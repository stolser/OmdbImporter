package com.stolser.controller;

import com.stolser.entity.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static com.stolser.ApplicationResources.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = IMPORT_FORM_URI)
public class ImportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportController.class);
    static final String YEAR_MIN_ATTR = "yearMin";
    static final String YEAR_MAX_ATTR = "yearMax";
    static final String VIDEO_TYPES_ATTR = "videoTypes";
    static final String IMPORT_RESULT_ATTR = "importResult";

    @Autowired
    private Environment env;

    @Autowired
    private VideoImporter videoImporter;

    @RequestMapping(method = GET)
    public String importFormPage(Model model) {
        model.addAttribute(YEAR_MIN_ATTR, env.getRequiredProperty("search.yearMin"));
        model.addAttribute(YEAR_MAX_ATTR, env.getRequiredProperty("search.yearMax"));
        model.addAttribute(VIDEO_TYPES_ATTR, getVideoTypes());

        return IMPORT_FORM_VIEW_NAME;
    }

    private List<Video.Type> getVideoTypes() {
        List<Video.Type> types = new ArrayList<>();
        types.add(Video.Type.MOVIE);
        types.add(Video.Type.SERIES);

        return types;
    }

    @RequestMapping(method = POST)
    public String processImport(Model model, SearchParameters params, Errors errors) {
        ProcessImportResult result;
        if (errors.hasErrors()) {
            LOGGER.debug("Error during validation. Errors: " + errors.getAllErrors());
            result = ProcessImportResult.ERROR;
        } else {
            LOGGER.debug("params = " + params);
            result = videoImporter.importVideo(params);
        }

        model.addAttribute(IMPORT_RESULT_ATTR, result);

        return REPORT_VIEW_NAME;
    }
}
