package com.stolser.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class RetrieveIdsTaskTest {

    @Mock
    private RawResultsSearcher resultsSearcher;

    @InjectMocks
    private RetrieveIdsTask task = new RetrieveIdsTask(1, 5,
            URI.create("http://www.omdbapi.com/?i=tt0612823&plot=full&tomatoes=true"));

    private MultiVideoResult result;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        result = mock(MultiVideoResult.class);

        when(resultsSearcher.searchRawResults(any(), any())).thenReturn(result);
    }

    @Test
    public void compute_ShouldReturnCorrectValues() throws Exception {
        specifyResultMockBehavior();

        List<String> computeReturnExpected = Arrays.asList("one", "two", "one", "two", "one", "two");

        List<String> computeReturnActual = task.compute();

        verify(resultsSearcher, times(5)).searchRawResults(any(), any());
        verify(result, times(5)).isSuccess();
        verify(result, times(3)).getImdbIds();

        Assert.assertEquals(computeReturnExpected, computeReturnActual);
    }

    private void specifyResultMockBehavior() {
        when(result.isSuccess())
                .thenReturn(true).thenReturn(true)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(false);

        when(result.getImdbIds()).thenReturn(Arrays.asList("one", "two"));
    }
}