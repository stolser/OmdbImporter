package com.stolser.controller;

import com.stolser.entity.Video;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.stolser.ApplicationResources.*;
import static com.stolser.controller.ImportController.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ImportControllerTest {
    @Autowired
    private Environment env;

    @Autowired
    @InjectMocks
    private ImportController importController;

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job job;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(importController).build();

    }

    @Test
    public void formPage_ShouldReturnPageWithForm() throws Exception {
        mockMvc.perform(get(IMPORT_FORM_URI))
                .andExpect(view().name(IMPORT_FORM_VIEW_NAME))
                .andExpect(model().attributeExists(YEAR_MIN_ATTR, YEAR_MAX_ATTR, VIDEO_TYPES_ATTR))
                .andExpect(model().attribute(YEAR_MIN_ATTR, env.getRequiredProperty("search.yearMin")))
                .andExpect(model().attribute(YEAR_MAX_ATTR, env.getRequiredProperty("search.yearMax")));
    }

    @Test
    public void formPage_ShouldReturnValidationFailedMessage() throws Exception {
        mockMvc.perform(post(IMPORT_FORM_URI))
                .andExpect(content().string(VALIDATION_ERROR_RESPONSE));
    }

    @Test
    public void formPage_ShouldReturnSuccessMessage_AndJobLauncher_ShouldCallJob() throws Exception {
        String searchText = "Terminator";
        String searchYear = "0";
        String searchTypeName = "MOVIE";
        mockMvc.perform(post(IMPORT_FORM_URI)
                .param(SEARCH_TEXT_PARAM, searchText)
                .param(SEARCH_YEAR_PARAM, searchYear)
                .param(SEARCH_VIDEO_TYPE_PARAM, searchTypeName))
                .andExpect(content().string(IMPORT_DATA_HAS_BEEN_STARTED_RESPONSE));

        verify(jobLauncher, times(1)).run(job, importController.getJobParameters(
                new SearchParameters(searchText, Integer.parseInt(searchYear),
                        Video.Type.valueOf(searchTypeName))));
    }

}