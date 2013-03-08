package org.framework.generators;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.design.fileutils.LargeFileReader;
import org.design.primitives.Poem;

/*
 * This will be the heavyweight class!
 */

public class PoetryGenerator 
{
	/*
	 * Generate a random poem of type poem.poemType
	 */
	public static Poem generatePoem(Poem poem)
	{
		/*
		 * Some logic here
		 */
		poem.setLines(Arrays.asList(new String [] {"Line 1 of poem", "Line 2 of poem"}));
		poem.setPoemTitle("Test");
		return poem;
	}
	
	/*
	 * Generate poetry using the words of a corpus.
	 * The corpus will be a huge text file for now.
	 * The type of poem = poem.poemType
	 */
	
	public static Poem generatePoemFromCorpus(Poem poem, String corpusFileName) throws IOException
	{
		List<String> lines_of_corpus = LargeFileReader.readFile(corpusFileName);
		/*
		 * Some logic here.... which operates on lines_of_corpus
		 */
		poem.setLines(Arrays.asList(new String [] {"Line 1 of poem", "Line 2 of poem"}));
		poem.setPoemTitle("Test");
		return poem;
	}

	public static Poem generatePoemFromTitle(Poem poem, String title) 
	{
		/*
		 * Some logic here
		 */
		poem.setLines(Arrays.asList(new String [] {"Line 1 of poem", "Line 2 of poem"}));
		poem.setPoemTitle(title);
		return poem;
	}
	
}
