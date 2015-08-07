package com.ck.autocomplete.utility;

import com.ck.autocomplete.charmapper.CharConverter;
import com.ck.autocomplete.charmapper.TurkishCharConverter;

public class StringNormalizer {

	private CharConverter charConverter;

	public StringNormalizer() {
		this.charConverter = new TurkishCharConverter();
	}

	public StringNormalizer(CharConverter charConverter) {
		this.charConverter = charConverter;
	}

	/**
	 * Normalize given word; Translate all characters to Latin characters,
	 * Remove all non alphanumeric characters, Convert to lower case
	 * 
	 * @param word
	 * @return
	 */

	public String normalize(String word) {
		word = charConverter.convertAll(word);
		word = word.replaceAll("[\\W]|_", "");
		word = word.toLowerCase();
		return word;
	}

	/**
	 * Normalize given word; Translate all characters to Latin characters,
	 * Remove all non alphanumeric characters except white spaces between words,
	 * Convert to lower case
	 * 
	 * @param word
	 * @return
	 */

	public String normalizeWithWhiteSpace(String word) {
		word = charConverter.convertAll(word);
		word = word.replaceAll("[^a-zA-Z0-9\\s]", " ");
		word = word.replaceAll("\\s+", " ");
		word = word.toLowerCase().trim();
		return word;
	}
}
