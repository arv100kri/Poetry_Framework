package org.framework.poemtypes;

import org.design.primitives.Poem;
import org.design.primitives.PoemCharacteristic;
import org.framework.interfaces.HaikuConstants;

public class HaikuPoem extends Poem
{
	
	public HaikuPoem()
	{
		this.setPoemType("Haiku Poem");
		
		PoemCharacteristic pc = new PoemCharacteristic.PoemCharacteristicBuilder().
				number_of_lines(HaikuConstants.number_of_lines).
				number_of_syllables(HaikuConstants.number_of_syllables).
				syllable_count_order(HaikuConstants.syllable_order).build();
		
		this.setPoemCharacteristic(pc);
	}
}
