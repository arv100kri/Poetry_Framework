package org.design.primitives;

import java.util.List;


abstract public class Poem 
{
	private String poemType;
	private String poemTitle;
	private PoemCharacteristic poemCharacteristic;
	private List<String> lines;
	
	public String getPoemTitle() {
		return poemTitle;
	}

	public void setPoemTitle(String poemTitle) {
		this.poemTitle = poemTitle;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public PoemCharacteristic getPoemCharacteristic() {
		return poemCharacteristic;
	}

	public void setPoemCharacteristic(PoemCharacteristic poemCharacteristic) {
		this.poemCharacteristic = poemCharacteristic;
	}

	public String getPoemType() {
		return poemType;
	}

	public void setPoemType(String poemType) {
		this.poemType = poemType;
	}

	@Override
	public String toString() 
	{
		String returner = "Poem Type= " + poemType+"\n";
		returner += "=================================================\n" + poemTitle + "\n=================================================\n";
		for(String line : this.lines)
		{
			returner += line+ "\n";
		}
		returner += "\n";
		return returner;
	}
}
