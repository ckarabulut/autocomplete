package com.ck.autocomplete.configuration;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ConfigurationTest {

    @Test
    public void test_initialize() {
        Configuration<Object> configuration = new Configuration<>(null, null, 0);
        Assert.assertNotNull(configuration);
        Assert.assertNull(configuration.getTokenizer());
        Assert.assertNull(configuration.getSearchElementLoader());
    }

    @Test
    public void test_custom_normalizer() {
        SearchElementLoader mock = Mockito.mock(SearchElementLoader.class);
        Configuration configuration = new Configuration<>(null, mock, 0);
        Assert.assertEquals(mock, configuration.getSearchElementLoader());
    }

    @Test
    public void test_custom_tokenizer() throws Exception {
        Tokenizer mock = Mockito.mock(Tokenizer.class);
        Configuration configuration = new Configuration<>(mock, null, 0);
        Assert.assertEquals(mock, configuration.getTokenizer());
    }


    @Test
    public void test_max_search_result() throws Exception {
        Configuration configuration = new Configuration<>(null, null, 5);
        Assert.assertEquals(5, configuration.getDefaultMaxSearchResult());
    }

}