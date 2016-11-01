package com.ck.autocomplete.suffixtree;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.element.SearchElement;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

/**
 * Created by caglar on 25-Oct-16.
 */
public class SuffixTreeFactoryTest {

    @Test
    public void test_initialize() {
        assertNotNull(new SuffixTreeFactory(null));
    }

    @Test
    public void test_initialize_suffix_tree_with_factory() {
        SearchElementLoader searchElementLoader = Mockito.mock(SearchElementLoader.class);
        Set<SearchElement> searchElements = new HashSet<>();

        Mockito.when(searchElementLoader.load()).thenReturn(searchElements);
        SuffixTreeFactory suffixTreeFactory = new SuffixTreeFactory(searchElementLoader);
        SuffixTree stringSuffixTree = suffixTreeFactory.create();

    }

}