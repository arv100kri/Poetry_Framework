package org.design.poetry;

import java.util.Arrays;
import java.util.List;

import org.design.primitives.Word;

public class RhymeDictionaryParser 
{
	/*
	 * Parse a line of the CMUDict
	 * Consider only words -- Ignoring punctuation marks and other special characters
	 * The format of a line in the CMUDict is 
	 * word<two spaces>{space separated list of syllables}
	 */
	public static Word parseLine(String line)
	{
		Word word = null;
		String[] word_syllables = line.split("  ");
		String word_alpha = word_syllables[0];
		if(Character.isLetter(word_alpha.charAt(0)))
		{
			List<String> syllables = Arrays.asList(word_syllables[1].split(" "));
			word = new Word(word_alpha, syllables, syllables.get(syllables.size() - 1));
		}
		return word;
	}
}