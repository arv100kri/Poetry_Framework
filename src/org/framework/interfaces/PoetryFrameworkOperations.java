package org.framework.interfaces;

import java.util.List;

import org.design.primitives.Poem;

/*
 * List of operations which will be performed by each class of type Poem.
 * Implementations will depend on the characteristics of each poem
 */
public interface PoetryFrameworkOperations 
{
	/*
	 * Function that generates a poetry given a title
	 */
	public List<String> generatePoetryFromTitle(String title);
	
	/*
	 * Function that generates a poetry from a corpus.
	 * The corpus must be contained within a text file (for now).
	 */
	public List<String> generatePoetryFromCorpus(String corpusFileName);
	
	/*
	 * Generate a poetry randomly!
	 */
	public List<String> generatePoetry();
	
	/*
	 * Check if the poem given as input is a valid poem type.
	 */
	public boolean isValidPoetry(List<String> poem);
	
	/*
	 * Generate a skeleton of the poetry type
	 */
	public Poem generatePoetrySkeleton();
	
	/*
	 * From a large text corpus, identify if there is a poem in it
	 */
	public List<Poem> identifyPoetryFromCorpus(String corpusFileName);
	
}
