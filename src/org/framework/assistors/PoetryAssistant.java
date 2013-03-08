package org.framework.assistors;

import java.util.Arrays;
import java.util.List;

import org.design.primitives.Poem;
import org.design.primitives.PoemSkeleton;

/*
 * Assist a human with poetry.
 * Heavyweight class!
 */
public class PoetryAssistant 
{
	/*
	 * Generate a skeleton of a particular poem if type given by poem.getType
	 * 
	 */
	public static PoemSkeleton generateSkeleton(Poem poem)
	{
		List<String> hints = 
				/*
				 * Some logic here!
				 */Arrays.asList(new String[] {"Hint 1", "Hint 2"});
		PoemSkeleton skeleton = new PoemSkeleton(poem, hints);
		return skeleton;
	}
}
