package org.framework.poemtypes;

import org.design.primitives.Poem;
import org.design.primitives.PoemCharacteristic;
import org.framework.interfaces.TankaConstants;

public class TankaPoem extends Poem
{
	
	public TankaPoem()
	{
		this.setPoemType("Tanka Poem");
		
		PoemCharacteristic pc = new PoemCharacteristic.PoemCharacteristicBuilder().
				number_of_lines(TankaConstants.number_of_lines).
				number_of_syllables(TankaConstants.number_of_syllables).
				syllable_count_order(TankaConstants.syllable_order).build();
		
		this.setPoemCharacteristic(pc);
	}
}
