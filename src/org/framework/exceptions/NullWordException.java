package org.framework.exceptions;

public class NullWordException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String word1;
	private String word2;
	
	public NullWordException(String word1, String word2) 
	{
		super();
		this.word1 = word1;
		this.word2 = word2;
	}

	@Override
	public String toString() 
	{
		return "NullWordException [word1=" + word1 + ", word2=" + word2 + "]";
	}
	
}
