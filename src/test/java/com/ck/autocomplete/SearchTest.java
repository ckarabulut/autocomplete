package com.ck.autocomplete;

import com.ck.autocomplete.configuration.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by caglar on 23-Oct-16.
 */
public class SearchTest {

    @Test
    public void test_initialize_search() {
        Search search = new SearchImpl<>(null);
        Assert.assertNotNull(search);
    }

    @Test
    public void test_search() {
        Configuration configuration = Mockito.mock(Configuration.class);
        Search<String> search = new SearchImpl<>(configuration);
        Assert.assertEquals("test", search.search("test").get(0).getValue());
    }


    @Test
    public void test_with_loader() {
//        Search<String> search = new SearchImpl<>()
    }

}
