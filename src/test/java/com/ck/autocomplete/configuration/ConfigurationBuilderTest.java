package com.ck.autocomplete.configuration;

import com.ck.autocomplete.configuration.loader.SearchElementLoader;
import com.ck.autocomplete.mapper.CharMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ConfigurationBuilderTest {

    @Test
    public void test_initialize() {
        Assert.assertNotNull(new ConfigurationBuilder<>(null));
    }

    @Test
    public void test_default_normalizer() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder<>(null);
        Assert.assertNotNull(configurationBuilder.getTokenizer());
    }

    @Test
    public void test_custom_normalizer() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder<>(null);
        Tokenizer tokenizer = Mockito.mock(Tokenizer.class);
        configurationBuilder.setTokenizer(tokenizer);
        Assert.assertEquals(tokenizer, configurationBuilder.getTokenizer());
    }


    @Test
    public void test_custom_char_mapper() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder<>(null);
        CharMapper charMapper = Mockito.mock(CharMapper.class);
        Mockito.when(charMapper.mapAll("AA")).thenReturn("AA");

        configurationBuilder.setCharMapper(charMapper);
        configurationBuilder.getTokenizer().tokenize("AA");
        Mockito.verify(charMapper).mapAll("AA");
    }

    @Test
    public void test_initialize_search_element_loader() throws Exception {
        SearchElementLoader<String> mock = Mockito.mock(SearchElementLoader.class);
        ConfigurationBuilder<String> configurationBuilder = new ConfigurationBuilder<>(mock);
        Assert.assertEquals(mock, configurationBuilder.getSearchElementLoader());
    }

    @Test
    public void test_build() {
        SearchElementLoader searchElementLoader = Mockito.mock(SearchElementLoader.class);
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder<>(searchElementLoader);

        Configuration build = configurationBuilder.build();
        Assert.assertEquals(searchElementLoader, build.getSearchElementLoader());
        Assert.assertEquals(StringTokenizer.class, build.getTokenizer().getClass());
    }

}