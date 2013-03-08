package org.framework.poemtypes;

import org.design.primitives.Poem;
import org.design.primitives.PoemCharacteristic;
import org.framework.interfaces.CoupletConstants;

public class CoupletPoem extends Poem 
{

	public CoupletPoem()
	{
		this.setPoemType("Couplet Poem");
		PoemCharacteristic pc = new PoemCharacteristic.PoemCharacteristicBuilder().
				number_of_lines(CoupletConstants.number_of_lines).
				rhymeScheme(CoupletConstants.rhymeScheme).build();
		this.setPoemCharacteristic(pc);
	}
}
