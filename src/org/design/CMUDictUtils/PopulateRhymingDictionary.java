package org.design.CMUDictUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.design.db.SingletonDatabaseConnection;
import org.design.primitives.Word;
import org.framework.interfaces.Constants;

/*
 * Populate the rhyming_dictionary table with the contents of CMUDict
 */

public class PopulateRhymingDictionary 
{
	private static final Logger LOGGER = Logger.getLogger("ErrorLogging_dict");
	
	public boolean populateDbSingleWord(Word word, SingletonDatabaseConnection myConnection)
	{
		return myConnection.insertWord(word);
	}
	
	public void populateDbWithDictionary(String dictionaryFile) throws IOException
	{
		SingletonDatabaseConnection myConnection = SingletonDatabaseConnection.getInstance(Constants.databaseUrl, Constants.userName, Constants.password);
		FileInputStream fstream = new FileInputStream(dictionaryFile);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		while((line = br.readLine()) != null)
		{
			Word word = RhymeDictionaryParser.parseLine(line);
			if(word != null)
			{
				if(!this.populateDbSingleWord(word, myConnection))
				{
					LOGGER.info("Some problem inserting into the DB! for the word: "+ word.getWord());
				}
			}
		}
		br.close();
		myConnection.closeConnection();
	}

	/*
	 * Done. Uncomment this to repopulate the database from the file.
	 * There is a dump of the database, which could be used instead. That would be faster.	
	public static void main(String [] args)
	{
		PopulateRhymingDictionary p = new PopulateRhymingDictionary();
		try 
		{
			LOGGER.info("Populating the database...");
			p.populateDbWithDictionary(Constants.CMUDICT);
		} 
		catch (IOException ex) 
		{
			LOGGER.info(ex.getMessage());
		}
		LOGGER.info("Done populating");
	}
*/	
}
