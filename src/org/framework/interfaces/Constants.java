package org.framework.interfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.framework.poemtypes.CoupletPoem;
import org.framework.poemtypes.HaikuPoem;
import org.framework.poemtypes.LimerickPoem;

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
	 * Types of poems: <String, Class>
	 * We will not be able to handle anything else except the ones listed below
	 */
	
	public static final String HAIKU_POEM = "Haiku Poem";
	public static final String LIMERICK_POEM = "Limerick Poem";
	public static final String COUPLET_POEM = "Couplet Poem";
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public static final Map<String, Class> poemTypes = 
			Collections.unmodifiableMap(new HashMap<String, Class>()
			{
				{
					put(HAIKU_POEM, HaikuPoem.class);
					put(LIMERICK_POEM, LimerickPoem.class);
					put(COUPLET_POEM, CoupletPoem.class);
				}
			});
}
