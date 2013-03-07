package org.global.interfaces;

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
}
