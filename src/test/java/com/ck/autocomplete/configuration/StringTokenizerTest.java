package com.ck.autocomplete.configuration;

import com.ck.autocomplete.mapper.CharMapper;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class StringTokenizerTest {

    @Test
    public void test_initialize() {
        assertNotNull(new StringTokenizer(null));
    }

    @Test
    public void test_normalize() {
        CharMapper mocked = Mockito.mock(CharMapper.class);
        String words = "  Test TEST test   ççğğ  ";
        Mockito.when(mocked.mapAll(words)).thenReturn("  Test TEST test   ccgg  ");
        Tokenizer tokenizer = new StringTokenizer(mocked);
        assertEquals("test test test ccgg", tokenizer.tokenize(words));
    }
}