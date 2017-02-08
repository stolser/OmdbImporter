package com.stolser.search;

import com.stolser.controller.SearchParameters;
import com.stolser.entity.Video;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class ConcurrentIdSearcherTest {

    @Mock
    private RawResultsSearcher rawSearcher;
    @Mock
    private MultiVideoResult mockResult;

    @InjectMocks
    IdSearcher idSearcher = new ConcurrentIdSearcher();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        mockResult = Mockito.mock(MultiVideoResult.class);

        when(rawSearcher.searchRawResults(any(), any())).thenReturn(mockResult);

    }

    @Test
    public void searchImdbIds_ShouldReturnEmptyList_WhenItFailed() throws Exception {
        when(mockResult.isSuccess()).thenReturn(false);

        SearchParameters params = new SearchParameters("Hello world", 2002, Video.Type.MOVIE);
        List<String> ids = new ConcurrentIdSearcher().searchImdbIds(params);

        Assert.assertEquals(0, ids.size());
    }
}