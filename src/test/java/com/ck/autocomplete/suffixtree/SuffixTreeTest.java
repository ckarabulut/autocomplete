package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by caglar on 25-Oct-16.
 */
public class SuffixTreeTest {

    @Test
    public void test_initialize_suffix_tree() {
        assertNotNull(new SuffixTree());
    }



}