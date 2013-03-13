package org.design.fileutils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Is there a clever way to read a large file?
 */
public class LargeFileReader 
{
	public static List<String> readFile(String fileName) throws IOException
	{
		FileInputStream fstream = new FileInputStream(fileName);
		
		DataInputStream in = new DataInputStream(fstream);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String strLine;
		List<String> lines = new ArrayList<String>();
		while ((strLine = br.readLine()) != null)   
		{
			lines.add(strLine);
		}
		
		in.close();
		return lines;
	}
	
	/*
	 * Sonal's parser....This needs to be used later when reading large corpora
	 */
	public static String GetLastWord(String path, int line_number) throws IOException
	{
		
		String line;
		String word = null;
		int count=0;
		
		FileInputStream f = new FileInputStream(path);
		DataInputStream in = new DataInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		while ((line = br.readLine()) != null) 
		{
		  word = line.substring(line.lastIndexOf(" ")+1);	
		  count++;
		  if(count==line_number)
			  break;
		}

		
		in.close();
		return word;
		
	}

	/*
	 * Arvind's slightly hackish method for the Deliverable 3
	 * Assuming each word is separated by a <space>
	 */

	public static List<String> getLastWord(List<String> lines) {
		List<String>lastWords = new ArrayList<String> ();
		for(String line: lines)
		{
			lastWords.add(line.substring(line.lastIndexOf(" ")+1));
		}
		return lastWords;
	}


	
}
