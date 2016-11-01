package com.ck.autocomplete.element;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by caglar on 26-Oct-16.
 */
public class SearchElementTest {

    @Test
    public void test_initialize_search_element() {
        assertNotNull(new SearchElement<>(null, null, null));
    }

}