package com.ck.autocomplete.mapper;

import java.util.HashMap;
import java.util.Map;

public class TurkishCharMapper implements CharMapper {

    private Map<Character, Character> map;

    public TurkishCharMapper() {
        map = new HashMap<>();
        map.put('\u00C7', 'C');
        map.put('\u011E', 'G');
        map.put('\u0130', 'I');
        map.put('\u00D6', 'O');
        map.put('\u015E', 'S');
        map.put('\u00DC', 'U');
        map.put('\u00E7', 'c');
        map.put('\u011F', 'g');
        map.put('\u0131', 'i');
        map.put('\u00F6', 'o');
        map.put('\u015F', 's');
        map.put('\u00FC', 'u');
    }

    @Override
    public String mapAll(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                chars[i] = map.get(chars[i]);
            }
        }
        return String.valueOf(chars);
    }

}
