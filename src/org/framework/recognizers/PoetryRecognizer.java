package org.framework.recognizers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.design.fileutils.LargeFileReader;
import org.design.poetryutils.SimpleRhymeWords;
import org.design.primitives.Poem;
import org.framework.exceptions.NullWordException;
import org.framework.interfaces.Constants;
import org.framework.poemtypes.CoupletPoem;
import org.framework.poemtypes.HaikuPoem;
import org.framework.poemtypes.LimerickPoem;
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
		 * Some logic here -- Hackish. Will only check for Haiku, Limerick or Couplet 
		 */
		List<String> lines = poem.getLines();
		String lw1, lw2, lw3, lw4, lw5;
		switch(poem.getPoemType())
		{
			case Constants.HAIKU_POEM:
				if(lines.size() !=3)	//Haiku has only 3 lines
				{
					return false;
				}
				int totalNumberOfSyllables = 0;
				List<Integer> syllableOrder = new ArrayList<Integer>();
				for(String line : lines)
				{
					List<String> words = Arrays.asList(line.split(" ")); //Assuming that words are separated by spaces
					String python_argument = "";
					for(String word: words)
					{
						python_argument +=word+",";
					}
					python_argument = python_argument.substring(0, python_argument.length() - 1);
					int syllables_in_line = SimpleRhymeWords.getSyllablesCount(python_argument);
					syllableOrder.add(syllables_in_line);
					totalNumberOfSyllables += syllables_in_line;
				}
				
				int actual_syllables = poem.getPoemCharacteristic().getNumber_of_syllables();
				List<Integer> actual_order = poem.getPoemCharacteristic().getSyllable_count_order();
				
				if(totalNumberOfSyllables!= actual_syllables || !syllableOrder.equals(actual_order))
				{
					return false;
				}
				
				return true;				
				
			case Constants.LIMERICK_POEM:
				List<String> lastWords = LargeFileReader.getLastWord(lines);
 				if(lines.size() !=5)	//Limerick has only five lines
				{
					return false;
				}
				//Hack -- need to incorporate rhyme scheme somehow
				lw1 = lastWords.get(0);
				lw2 = lastWords.get(1);
				lw3 = lastWords.get(2);
				lw4 = lastWords.get(3);
				lw5 = lastWords.get(4);
				
			try {
				if(SimpleRhymeWords.isRhyming(lw1, lw2) && SimpleRhymeWords.isRhyming(lw3, lw4) 
						&& SimpleRhymeWords.isRhyming(lw1, lw5))
				{
					return true;
				}
			} catch (NullWordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
				return false;
				
			case Constants.COUPLET_POEM:
				lastWords = LargeFileReader.getLastWord(lines);
				if(lines.size() != 2) //Couplet has only two lines
				{
					return false;
				}
				lw1 = lastWords.get(0);
				lw2 = lastWords.get(1);
			try {
				if(SimpleRhymeWords.isRhyming(lw1, lw2))
				{
					return true;
				}
			} catch (NullWordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
				return false;
		}
		
		return true;
	}
	
	/*
	 * Return a list of poems of type = poem.poemtype
	 * from the corpus
	 */
	public static List<Poem> identifyPoemsFromCorpus(String corpusFileName, Poem poem) throws IOException
	{
		List<Poem> potentialCoupletPoems = new ArrayList<Poem>();
		List<Poem> potentialLimerickPoems = new ArrayList<Poem>();
		List<Poem> potentialHaikuPoems = new ArrayList<Poem>();
		List<String> lines = LargeFileReader.readFile(corpusFileName);
		int i =0;
		for(; i<lines.size()-4; i++)
		{
			if(poem.getPoemType() == Constants.COUPLET_POEM)
			{
				Poem potentialCoupletPoem = new CoupletPoem();
				List<String>couplet = Arrays.asList(new String[]{lines.get(i),lines.get(i+1)});
				potentialCoupletPoem.setLines(couplet);
				potentialCoupletPoem.setPoemTitle("Title");
				potentialCoupletPoems.add(potentialCoupletPoem);
			}
			else if(poem.getPoemType() == Constants.HAIKU_POEM)
			{
				Poem potentialHaikuPoem = new HaikuPoem();
				List<String>haiku = Arrays.asList(new String[]{lines.get(i), lines.get(i+1), lines.get(i+2)});
				potentialHaikuPoem.setLines(haiku);
				potentialHaikuPoem.setPoemTitle("Title");
				potentialHaikuPoems.add(potentialHaikuPoem);
			}
			else if(poem.getPoemType() == Constants.LIMERICK_POEM)
			{
				Poem potentialLimerickPoem = new LimerickPoem();
				List<String>limerick = Arrays.asList(new String[]{lines.get(i), lines.get(i+1), lines.get(i+2), lines.get(i+3), lines.get(i+4)});
				potentialLimerickPoem.setLines(limerick);
				potentialLimerickPoem.setPoemTitle("Title");
				potentialLimerickPoems.add(potentialLimerickPoem);
			}
		}
		
		for(;i<lines.size()-2;i++)
		{
			if(poem.getPoemType() == Constants.COUPLET_POEM)
			{
				Poem potentialCoupletPoem = new CoupletPoem();
				List<String>couplet = Arrays.asList(new String[]{lines.get(i),lines.get(i+1)});
				potentialCoupletPoem.setLines(couplet);
				potentialCoupletPoem.setPoemTitle("Title");
				potentialCoupletPoems.add(potentialCoupletPoem);
			}
			else if(poem.getPoemType() == Constants.HAIKU_POEM)
			{
				Poem potentialHaikuPoem = new HaikuPoem();
				List<String>haiku = Arrays.asList(new String[]{lines.get(i), lines.get(i+1), lines.get(i+2)});
				potentialHaikuPoem.setLines(haiku);
				potentialHaikuPoem.setPoemTitle("Title");
				potentialHaikuPoems.add(potentialHaikuPoem);
			}
		}
		
		for(;i<lines.size()-1;i++)
		{
			if(poem.getPoemType() == Constants.COUPLET_POEM)
			{
				Poem potentialCoupletPoem = new CoupletPoem();
				List<String>couplet = Arrays.asList(new String[]{lines.get(i),lines.get(i+1)});
				potentialCoupletPoem.setLines(couplet);
				potentialCoupletPoem.setPoemTitle("Title");
				potentialCoupletPoems.add(potentialCoupletPoem);
			}
		}

		List<Poem> poems_in_corpus = new ArrayList<Poem> ();
		switch(poem.getPoemType())
		{
			case Constants.HAIKU_POEM:
				for(Poem p: potentialHaikuPoems)
				{
					//System.out.println(p);
					if(PoetryRecognizer.isValidPoetry(p))
					{
						poems_in_corpus.add(p);
					}
				}break;
			
			case Constants.COUPLET_POEM:
				for(Poem p: potentialCoupletPoems)
				{
					//System.out.println(p);
					if(PoetryRecognizer.isValidPoetry(p))
					{
						poems_in_corpus.add(p);
					}
				}break;
			
			case Constants.LIMERICK_POEM:
				for(Poem p: potentialLimerickPoems)
				{
					//System.out.println(p);
					if(PoetryRecognizer.isValidPoetry(p))
					{
						poems_in_corpus.add(p);
					}
				}break;
		}
		
		if(poems_in_corpus.size() == 0)
			return Collections.emptyList();
			
		return poems_in_corpus;
	}
}
