package com.stolser.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.stolser.ApplicationResources.IMPORT_FORM_URI;
import static com.stolser.ApplicationResources.IMPORT_FORM_VIEW_NAME;
import static com.stolser.controller.ImportController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ImportControllerTest {
    @Autowired
    private Environment env;

    @Autowired
//    @InjectMocks
    private ImportController importController;

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

}