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
	
}
