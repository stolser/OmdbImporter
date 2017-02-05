package com.stolser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stolser.controller.ProcessImportResult;

public class Tester {
    public static void main(String[] args) throws Exception {
        ProcessImportResult response = new ProcessImportResult(true, ProcessImportResult.RESULT_SUCCESS, 11);
        String jsonResponse = new ObjectMapper().writeValueAsString(response);
        System.out.println("jsonResponse = " + jsonResponse);
    }
}
