package org.design.framework;

import java.util.List;
import java.util.logging.Logger;

import org.design.db.SingletonDatabaseConnection;
import org.design.primitives.Word;
import org.framework.exceptions.NullWordException;
import org.framework.interfaces.Constants;

public class SimpleRhymeWords 
{
	private static final Logger LOGGER = Logger.getLogger("ErrorLogging_simplerhyme");
	
	/*
	 * Get [numberOfWords] words which rhyme with the input word
	 */
	public static List<Word> getRhymingWords(String word, int numberOfWords)
	{
		SingletonDatabaseConnection myConnection = SingletonDatabaseConnection.getInstance(Constants.databaseUrl, Constants.userName, Constants.password);
		Word word_alpha = myConnection.selectWord(word);
		
		List<Word> words = myConnection.selectRhymingWords(word_alpha, numberOfWords);
		if(words.isEmpty())
		{
			LOGGER.info("Could not find rhyming words");
		}
		myConnection.closeConnection();
		return words;
	}
	
	/*
	 * Get 10 words which rhyme with the input word
	 */
	public static List<Word> getRhymingWords(String word)
	{
		SingletonDatabaseConnection myConnection = SingletonDatabaseConnection.getInstance(Constants.databaseUrl, Constants.userName, Constants.password);
		Word word_alpha = myConnection.selectWord(word);
		
		List<Word> words = myConnection.selectRhymingWords(word_alpha, Constants.DEFAULT_LIMIT);
		if(words.isEmpty())
		{
			LOGGER.info("Could not find rhyming words");
		}
		myConnection.closeConnection();
		return words;
	}
	
	/*
	 * Check if word_alpha1 and word_alpha2 rhyme with each other.
	 * Rule to check --> Equality of last syllables
	 */	
	public static boolean isRhyming(String word_alpha1, String word_alpha2) throws NullWordException
	{
		SingletonDatabaseConnection myConnection = SingletonDatabaseConnection.getInstance(Constants.databaseUrl, Constants.userName, Constants.password);
		Word word1 = myConnection.selectWord(word_alpha1);
		Word word2 = myConnection.selectWord(word_alpha2);
		boolean rhyme = false;
		if(word1 == null || word2 == null)
		{
			LOGGER.info("Cannot determine the relation between the two words: "+ word_alpha1+" and "+ word_alpha2);
			String exception_word1 = "", exception_word2 = "";
			if(word1 == null)
			{
				exception_word1 = "Cannot find: "+ word_alpha1 +" in the database";
			}
			
			if(word2 == null)
			{
				exception_word2 = "Cannot find: "+ word_alpha2 +" in the database";
			}
			
			myConnection.closeConnection();
			
			throw new NullWordException(exception_word1, exception_word2);
		}
		
		else
		{
			if(word1.getLast_syllable().equals(word2.getLast_syllable()))
			{
				rhyme = true;
			}
		}
		myConnection.closeConnection();
		return rhyme;
	}
	
	/*
	public static void main(String [] args)
	{
		try 
		{
			System.out.println(getRhymingWords("Exception"));
		}
		catch (Exception e) 
		{
			LOGGER.info(e.toString());
		}
	}
	*/
}