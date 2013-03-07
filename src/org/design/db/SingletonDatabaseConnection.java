package org.design.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.design.primitives.Word;

public class SingletonDatabaseConnection
{
	private static Connection connection = null;
	
	private static final Logger LOGGER = Logger.getLogger("ErrorLogging");
	
	private SingletonDatabaseConnection(String databaseUrl, String userName, String password)
	{
		//singleton
		try
		{
			if(connection == null || connection.isClosed())
			{
				connection = DriverManager.getConnection(databaseUrl, userName, password);
			}
		}
		catch(SQLException e)
		{
			  LOGGER.info("Some problem with the connection");
			  LOGGER.info(e.getMessage());
		}
	}
	
	public static SingletonDatabaseConnection getInstance(String databaseUrl, String userName, String password)
	{
		return new SingletonDatabaseConnection(databaseUrl, userName, password);
	}
	
	/*
	 * Insert a Word <word, list_of_syllables, last_syllable> into the rhyming_dictionary
	 */
	public boolean insertWord(Word word)
	{
		String insertDb = "insert into rhyming_dictionary(word, syllables, last_syllable) values (?,?,?)";
		PreparedStatement insert = null;
		boolean returner = false;
		try
		{
			insert = connection.prepareStatement(insertDb);
			insert.setString(1, word.getWord());
			insert.setString(2, word.getSyllablesAsString());
			insert.setString(3, word.getLast_syllable());
			insert.executeUpdate();
			returner = true;
		}
		
		catch(SQLException ex)
		{
			LOGGER.info(ex.getMessage());
			return returner;
		}
		return returner;		
	}
	
	/*
	 * This function returns the database details of a word. This could potentially return more than one row
	 * For instance:
	 * 	selectWords('AARONSON') = ['AARONSON', 'AARONSON(1)'] 
	 * 	selectWords('AACHEN') = ['AACHEN']
	 * 	and the other details for each element in the list
	 */
	
	public List<Word> selectWords(String word_alpha)
	{
		String selectDb = "select word, syllables, last_syllable from rhyming_dictionary where word like ?%";
		PreparedStatement select = null;
		List<Word> words = new ArrayList<Word>();
		try
		{
			select = connection.prepareStatement(selectDb);
			select.setString(1, word_alpha);
			ResultSet rs = select.executeQuery();
			{
				while(rs.next())
				{
					String word_name = rs.getString("word");
					List<String> syllables = Arrays.asList(rs.getString("syllables").split(" "));
					String last_syllable = rs.getString("last_syllable");
					Word word = new Word(word_name, syllables, last_syllable);
					words.add(word);
				}
			}
			
		}
		catch(SQLException ex)
		{
			LOGGER.info(ex.getMessage());
			return Collections.emptyList();
		}
		return words;
	}
	
	/*
	 * This function returns the database details of a word. This will only return one tuple of the form
	 * 
	 * 	selectWords('AARONSON') = ('AARONSON', 'EH1 R AH0 N S AH0 N', 'N')
	 * 	
	 */
	
	public Word selectWord(String word_alpha)
	{
		String selectDb = "select word, syllables, last_syllable from rhyming_dictionary where word = ?";
		PreparedStatement select = null;
		Word word = null;
		try
		{
			select = connection.prepareStatement(selectDb);
			select.setString(1, word_alpha);
			ResultSet rs = select.executeQuery();
			rs.next();
			String word_name = rs.getString("word");
			List<String> syllables = Arrays.asList(rs.getString("syllables").split(" "));
			String last_syllable = rs.getString("last_syllable");
			word = new Word(word_name, syllables, last_syllable);
		}
		catch(SQLException ex)
		{
			LOGGER.info(ex.getMessage());
			return word;
		}
		return word;
	}
	
	
	/*
	 * Returns a list of Word objects, which rhyme with the input word. Number of words returned = limit
	 * Rule for checking rhyme --> equality of last syllable
	 * limit is the number of rhyming words to return
	 */	
	public List<Word> selectRhymingWords(Word word_alpha, int limit)
	{
		String selectRhymes = "select word, syllables, last_syllable from rhyming_dictionary where last_syllable = ? limit ?";
		PreparedStatement select = null;
		List<Word> words = new ArrayList<Word>();
		try
		{
			select = connection.prepareStatement(selectRhymes);
			select.setString(1, word_alpha.getLast_syllable());
			select.setInt(2, limit);
			ResultSet rs = select.executeQuery();
			{
				while(rs.next())
				{
					String word_name = rs.getString("word");
					List<String> syllables = Arrays.asList(rs.getString("syllables").split(" "));
					String last_syllable = rs.getString("last_syllable");
					Word word = new Word(word_name, syllables, last_syllable);
					words.add(word);
				}
			}
			
		}
		catch(SQLException ex)
		{
			LOGGER.info(ex.getMessage());
			return Collections.emptyList();
		}
		return words;
	}
	
	public void closeConnection()
	{
		try 
		{
			connection.close();
		} 
		catch (SQLException ex) 
		{
			LOGGER.info(ex.getMessage());
		}
	}
}
