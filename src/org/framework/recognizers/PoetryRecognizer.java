package org.framework.recognizers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.design.fileutils.LargeFileReader;
import org.design.primitives.Poem;
/*
 * Heavyweight class!
 * Need to add more methods 
 */
public class PoetryRecognizer 
{
	/*
	 * Based on the PoetryCharacteristics check if the lines of the poem object
	 * constitute a valid poem
	 */
	public static boolean isValidPoetry(Poem poem)
	{
		/*
		 * Some logic here
		 */
		return true;
	}
	
	/*
	 * Return a list of poems of type = poem.poemtype
	 * from the corpus
	 */
	public static List<Poem> identifyPoemsFromCorpus(String corpusFileName, Poem poem) throws IOException
	{
		List<String> lines_of_corpus = LargeFileReader.readFile(corpusFileName);
		/*
		 * Some logic here... Need to identify poems occurring in the text...
		 * which operates on lines_of_corpus
		 */
		List<Poem> poems_in_corpus = new ArrayList<Poem> ();
		/*
		 * if count > 0 --> return
		 * else --> return Collections.empty_list()
		 */
		return poems_in_corpus;
	}
}
