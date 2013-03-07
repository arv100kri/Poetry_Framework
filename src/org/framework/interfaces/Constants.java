package org.framework.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.framework.poemtypes.HaikuPoem;

public interface Constants 
{
	/*
	 * Database details are the only members of this class
	 */
	public static final String databaseUrl = "jdbc:mysql://localhost:3306/Poetry";
	public static final String userName = "root";
	public static final String password = "password";
	
	/*
	 * Location of the CMUDict file
	 */
	
	public static final String CMUDICT = "resources/cmudict.0.7a";
	public static final int DEFAULT_LIMIT = 10;
	
	/*
	 * Types of poems: <String, Class> --May or may not be necessary.
	 * I am adding a simpler list below.
	 */
	@SuppressWarnings({ "serial", "rawtypes" })
	public static final Map<String, Class> poemTypes = 
			Collections.unmodifiableMap(new HashMap<String, Class>()
			{
				{
					put("Haiku Poem", HaikuPoem.class);
				}
			});
	
	@SuppressWarnings("serial")
	public static final List<String> poetrytypes =
			Collections.unmodifiableList(new ArrayList<String>()
			{		
				{
					add("Haiku Poem");
				}
			});
	
}
