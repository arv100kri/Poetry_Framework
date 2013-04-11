package org.framework.interfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.framework.poemtypes.CoupletPoem;
import org.framework.poemtypes.HaikuPoem;
import org.framework.poemtypes.LimerickPoem;
import org.framework.poemtypes.SonnetPoem;
import org.framework.poemtypes.TankaPoem;

public interface Constants 
{
	/*
	 * Database details are the only members of this class
	 */
	public static final String databaseUrl = "jdbc:mysql://localhost:3306/poetry_framework";
	public static final String userName = "root";
	public static final String password = "";
	
	/*
	 * Location of the CMUDict file
	 */
	
	public static final String CMUDICT = "resources/cmudict.0.7a";
	public static final int DEFAULT_LIMIT = 10;
	
	/*
	 * Types of poems: <String, Class>
	 * We will not be able to handle anything else except the ones listed below.
	 * We will be adding newer ones as we progress.
	 * 
	 * NOTE: Two additions are required for every new poetry type
	 */
	
	public static final String HAIKU_POEM = "Haiku Poem";
	public static final String LIMERICK_POEM = "Limerick Poem";
	public static final String COUPLET_POEM = "Couplet Poem";
	public static final String TANKA_POEM = "Tanka Poem";
	public static final String SONNET_POEM = "Sonnet Poem";
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public static final Map<String, Class> poemTypes = 
			Collections.unmodifiableMap(new HashMap<String, Class>()
			{
				{
					put(HAIKU_POEM, HaikuPoem.class);
					put(LIMERICK_POEM, LimerickPoem.class);
					put(COUPLET_POEM, CoupletPoem.class);
					put(TANKA_POEM, TankaPoem.class);
					put(SONNET_POEM, SonnetPoem.class);
				}
			});
}
