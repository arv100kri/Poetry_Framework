package org.design.primitives;

import java.util.List;

public class Word 
{
	private String word;
	private List<String> syllables;
	private String last_syllable;
	
	
	public Word(String word, List<String> syllables, String last_syllable) 
	{
		this.word = word;
		this.syllables = syllables;
		this.last_syllable = last_syllable;
	}
	
	
	@Override
	public String toString() {
		return word + "," + syllables + ", last_syllable=" + last_syllable + "]";
	}


	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<String> getSyllables() {
		return syllables;
	}
	public void setSyllables(List<String> syllables) {
		this.syllables = syllables;
	}
	public String getLast_syllable() {
		return last_syllable;
	}
	public void setLast_syllable(String last_syllable) {
		this.last_syllable = last_syllable;
	}
	
	public String getSyllablesAsString()
	{
		String syl_list = "";
		for(String syllable: syllables)
		{
			syl_list += syllable + " ";
		}
		return syl_list;
	}
	
}
