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
	
	
}
