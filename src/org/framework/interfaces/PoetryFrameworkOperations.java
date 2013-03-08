package org.framework.interfaces;

import java.io.IOException;
import java.util.List;

import org.design.primitives.Poem;
import org.design.primitives.PoemSkeleton;
import org.framework.exceptions.UnhandledPoemException;

/*
 * List of all the functions which we promised in Deliverable 1 and 2.
 * A few might be missing for now, but will be added
*/
public interface PoetryFrameworkOperations 
{
	/*
	 * Function that generates a poetry given a title
	 */
	public Poem generatePoetryFromTitle(String title, String poemType) throws UnhandledPoemException;
	
	/*
	 * Function that generates a poetry from a corpus.
	 * The corpus must be contained within a text file (for now).
	 */
	public Poem generatePoetryFromCorpus(String corpusFileName, String poemType) throws IOException, UnhandledPoemException;
	
	/*
	 * Generate a poetry randomly!
	 */
	public Poem generatePoetry(String poemType) throws UnhandledPoemException;
	
	/*
	 * Check if the poem given as input is a valid poem type.
	 */
	public boolean isValidPoetry(List<String> poem, String poemType) throws UnhandledPoemException;
	
	/*
	 * Generate a skeleton of the poetry type
	 */
	public PoemSkeleton generatePoetrySkeleton(String poemType) throws UnhandledPoemException;
	
	/*
	 * From a large text corpus, identify if there are poems in it
	 * If yes return them else return null
	 */
	public List<Poem> identifyPoetryFromCorpus(String corpusFileName, String poemType) throws UnhandledPoemException;
	
}
