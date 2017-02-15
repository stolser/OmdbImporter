package com.stolser.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.Mockito.*;

public class RawResultsSearcherImplTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    private RawResultsSearcherImpl resultsSearcher;

    private URI searchUri;
    private Class<SingleVideoResult> resultsClass;
    private ResponseEntity<SingleVideoResult> responseEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchUri = URI.create("http://www.omdbapi.com/?i=tt0472954");
        resultsClass = SingleVideoResult.class;
        responseEntity = Mockito.mock(ResponseEntity.class);
    }

    @Test
    public void searchRawResults_CallsGetForEntityOnce_WhenTheFirst_StatusCode_IsOk() throws Exception {
        when(responseEntity.getStatusCode())
                .thenReturn(HttpStatus.OK);
        when(restTemplate.getForEntity(searchUri, resultsClass)).thenReturn(responseEntity);

        resultsSearcher.searchRawResults(searchUri, resultsClass);

        verify(restTemplate, times(1)).getForEntity(searchUri, resultsClass);
        verify(responseEntity, times(1)).getStatusCode();
        verify(responseEntity, times(1)).getBody();
    }

    @Test
    public void searchRawResults_CallsGetForEntityThreeTimes_WhenTheThird_StatusCode_IsOk() throws Exception {
        when(responseEntity.getStatusCode())
                .thenReturn(HttpStatus.BAD_REQUEST)
                .thenReturn(HttpStatus.FORBIDDEN)
                .thenReturn(HttpStatus.OK);
        when(restTemplate.getForEntity(searchUri, resultsClass)).thenReturn(responseEntity);

        resultsSearcher.searchRawResults(searchUri, resultsClass);

        verify(restTemplate, times(3)).getForEntity(searchUri, resultsClass);
        verify(responseEntity, times(3)).getStatusCode();
        verify(responseEntity, times(1)).getBody();
    }
}