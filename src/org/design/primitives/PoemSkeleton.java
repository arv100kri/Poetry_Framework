package org.design.primitives;

import java.util.List;

public class PoemSkeleton 
{
	Poem poem;
	List<String> hints;
	
	public PoemSkeleton(Poem poem, List<String> hints) 
	{
		this.poem = poem;
		this.hints = hints;
	}
	
	public Poem getPoem() {
		return poem;
	}
	public void setPoem(Poem poem) {
		this.poem = poem;
	}
	public List<String> getHints() {
		return hints;
	}
	public void setHints(List<String> hints) {
		this.hints = hints;
	}
	
	@Override
	public String toString() 
	{
		String returner = "Poem Type= " + poem.getPoemType()+"\n\n";
		returner += poem.getPoemTitle() + "\n=================================================\n\n";
		for(String line : poem.getLines())
		{
			returner += line + "\n";
		}
		
		returner += "\n\n====================HINTS======================\n\n";
		for(String hint : this.getHints())
		{
			returner += hint+"\n";
		}
		
		return returner;
	}

	
}
