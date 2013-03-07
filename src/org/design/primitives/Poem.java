package org.design.primitives;

import java.util.List;


abstract public class Poem 
{
	private String poemType;
	private PoemCharacteristic poemCharacteristic;
	private List<String> lines;
	
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
	
}
