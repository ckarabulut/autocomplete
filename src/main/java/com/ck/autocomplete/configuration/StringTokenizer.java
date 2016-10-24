package com.ck.autocomplete.configuration;

import com.ck.autocomplete.mapper.CharMapper;

public class StringTokenizer implements Tokenizer {

    private final CharMapper charMapper;

    public StringTokenizer(CharMapper charMapper) {
        this.charMapper = charMapper;
    }

    /**
     * Normalize given words; Translate all characters to Latin characters,
     * Remove all non alphanumeric characters except white spaces between words,
     * Convert to lower case
     *
     * @param words
     * @return
     */
    @Override
    public String[] tokenize(String words) {
        words = charMapper.mapAll(words);
        words = words.replaceAll("[^a-zA-Z0-9\\s]", " ");
        words = words.replaceAll("\\s+", " ");
        words = words.toLowerCase().trim();
        return words.split("\\s");
    }
}
