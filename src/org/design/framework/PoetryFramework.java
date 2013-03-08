package org.design.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.design.primitives.Poem;
import org.design.primitives.PoemSkeleton;
import org.framework.assistors.PoetryAssistant;
import org.framework.exceptions.UnhandledPoemException;
import org.framework.generators.PoetryGenerator;
import org.framework.interfaces.Constants;
import org.framework.interfaces.PoetryFrameworkOperations;
import org.framework.recognizers.PoetryRecognizer;

/*
 * Driver class for piecing together all the functionalities
 */

public class PoetryFramework implements PoetryFrameworkOperations
{
	
	private static final Logger LOGGER = Logger.getLogger("framework_logger");
	
	@Override
	public Poem generatePoetryFromTitle(String title, String poemType) throws UnhandledPoemException
	{
		Poem ai_poem = null;
		try
		{
			Poem poem = (Poem) Constants.poemTypes.get(poemType).newInstance();
			ai_poem = PoetryGenerator.generatePoemFromTitle(poem, title);
		}

		catch(Exception ex)
		{
			throw new UnhandledPoemException(poemType);
		}
		return ai_poem;
	}

	@Override
	public Poem generatePoetryFromCorpus(String corpusFileName, String poemType) throws IOException, UnhandledPoemException
	{
		Poem ai_poem = null;
		try
		{
			Poem poem = (Poem) Constants.poemTypes.get(poemType).newInstance();
			ai_poem = PoetryGenerator.generatePoemFromCorpus(poem, corpusFileName);
		}
		catch(IOException ex)
		{
			throw ex;
		}
		catch(Exception ex)
		{
			throw new UnhandledPoemException(poemType);
		}
		return ai_poem;
	}

	@Override
	public Poem generatePoetry(String poemType) throws UnhandledPoemException
	{
		Poem ai_poem = null;
		try
		{
			Poem poem = (Poem) Constants.poemTypes.get(poemType).newInstance();
			ai_poem = PoetryGenerator.generatePoem(poem);
		}
		catch(Exception ex)
		{
			throw new UnhandledPoemException(poemType);
		}
		return ai_poem;
	}

	@Override
	public boolean isValidPoetry(List<String> poem, String poemType) throws UnhandledPoemException {
		boolean valid = false;
		try
		{
			Poem poemToValidate = (Poem) Constants.poemTypes.get(poemType).newInstance();
			poemToValidate.setLines(poem);
			valid = PoetryRecognizer.isValidPoetry(poemToValidate);
		}
		catch(Exception ex)
		{
			throw new UnhandledPoemException(poemType);
		}
		return valid;
	}

	@Override
	public PoemSkeleton generatePoetrySkeleton(String poemType) throws UnhandledPoemException 
	{
		PoemSkeleton ps = null;
		Poem poem = null;
		try 
		{
			poem = (Poem) Constants.poemTypes.get(poemType).newInstance();
			ps = PoetryAssistant.generateSkeleton(poem);
		}
		
		catch (Exception ex) 
		{
			throw new UnhandledPoemException(poemType);
		}
		return ps;
	}

	@Override
	public List<Poem> identifyPoetryFromCorpus(String corpusFileName, String poemType) throws UnhandledPoemException 
	{
		Poem poem = null;
		List <Poem> poems = new ArrayList<Poem>();
		try
		{
			poem = (Poem) Constants.poemTypes.get(poemType).newInstance();
			poems = PoetryRecognizer.identifyPoemsFromCorpus(corpusFileName, poem);			
		}
		
		catch(Exception ex)
		{
			throw new UnhandledPoemException(poemType);
		}
		
		return poems;
	}

/*	
	public static void main(String [] args)
	{
		PoetryFramework pf = new PoetryFramework();
		String poetry_type = Constants.HAIKU_POEM;
		String corpusFileName = "resources/Wikipedia_text/Limerick";
		String title = "Dummy title";
		List<String> dummyLines = Arrays.asList(new String [] {"Line 1 of poem", "Line 2 of poem"});
		try
		{
			System.out.println(pf.generatePoetrySkeleton(poetry_type));
		}
		catch(UnhandledPoemException ex)
		{
			LOGGER.info(ex.toString());
		}
		
		catch(Exception ex)
		{
			LOGGER.info(ex.getMessage());
		}
	}
*/	
}
