package com.ck.autocomplete;

import com.ck.autocomplete.configuration.Configuration;
import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by caglar on 23-Oct-16.
 */
public class SearchTest {

    @Test
    public void test_initialize_search() {
        Configuration configuration = Mockito.mock(Configuration.class);
        SearchElementLoader searchElementLoader = Mockito.mock(SearchElementLoader.class);
        Mockito.when(configuration.getSearchElementLoader()).thenReturn(searchElementLoader);
        Search search = new SearchImpl(configuration);
        Assert.assertNotNull(search);
    }

    @Test
    public void test_search() {
        Configuration<String> configuration = Mockito.mock(Configuration.class);
        Search<String> search = new SearchImpl<>(configuration);
        Assert.assertEquals("test", search.search("test").get(0).getValue());
    }

}
