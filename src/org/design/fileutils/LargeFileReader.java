package org.design.fileutils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
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
			if(strLine.length()<1)
				continue;
			lines.add(strLine);
		}
		
		in.close();
		return lines;
	}
	
	public static String readFileIntoString(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
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
			String last_word = line.substring(line.lastIndexOf(" ")+1);
			lastWords.add(sanitize(last_word));
		}
		return lastWords;
	}
	
	public static String sanitize(String word)
	{
		for(int i=0; i<word.length(); i++)
		{
			if(!Character.isAlphabetic(word.charAt(i)))
			{
				word = word.replace(""+word.charAt(i), "");
				--i;
			}
		}
		return word;
	}
	
}
