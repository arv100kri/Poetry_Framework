package org.framework.poemtypes;

import org.design.primitives.Poem;
import org.design.primitives.PoemCharacteristic;
import org.framework.interfaces.LimerickConstants;

/*
 * In addition a limerick needs to have the same number of syllables in the
 * third and fourth lines.
 */
public class LimerickPoem extends Poem
{
	
	public LimerickPoem()
	{
		this.setPoemType("Limerick Poem");
		PoemCharacteristic pc = new PoemCharacteristic.PoemCharacteristicBuilder().
				number_of_lines(LimerickConstants.number_of_lines).
				rhymeScheme(LimerickConstants.rhymeScheme).build();
		this.setPoemCharacteristic(pc);								
	}
}
