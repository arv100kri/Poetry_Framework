package org.framework.exceptions;

public class UnhandledPoemException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -576457308757727918L;
	private String poemType;
	
	public UnhandledPoemException(String ptype) 
	{
		super();
		poemType = ptype;
	}

	@Override
	public String toString() 
	{
		return "Cannot handle poems of the type: "+ poemType;
	}
}
