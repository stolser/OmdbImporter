package com.stolser.search;

import com.stolser.controller.SearchParameters;
import com.stolser.entity.VideoType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ConcurrentIdSearcherTest {
    @Mock
    private RawResultsSearcher rawSearcher;
    @Mock
    private MultiVideoResult mockResult;
    @Mock
    private ForkJoinPool pool;

    @InjectMocks
    IdSearcher idSearcher = new ConcurrentIdSearcher();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(rawSearcher.searchRawResults(any(), any())).thenReturn(mockResult);

    }

    @Test
    public void searchImdbIds_ShouldReturnEmptyList_WhenItFailed() throws Exception {
        when(mockResult.isSuccess()).thenReturn(false);

        List<String> ids = idSearcher.searchImdbIds(getDummyParams());

        Assert.assertEquals(0, ids.size());
    }

    private SearchParameters getDummyParams() {
        return new SearchParameters("Hello world", 2002, VideoType.MOVIE);
    }

    @Ignore
    @Test(expected = RuntimeException.class)
    public void searchImdbIds_WhenResultIsSuccess_PoolIsInvoked_AndExceptionIsThrown() {
        when(mockResult.isSuccess()).thenReturn(true);

        idSearcher.searchImdbIds(getDummyParams());

        verify(pool, times(1)).invoke(any());
        verify(pool, times(1)).shutdown();
    }
}