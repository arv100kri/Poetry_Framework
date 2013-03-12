package org.framework.poemtypes;

import org.design.primitives.Poem;
import org.design.primitives.PoemCharacteristic;
import org.framework.interfaces.SonnetConstants;


public class SonnetPoem extends Poem
{

  public SonnetPoem()
  {
    this.setPoemType("Sonnet Poem");
    PoemCharacteristic pc = new PoemCharacteristic.PoemCharacteristicBuilder().
        number_of_lines(SonnetConstants.number_of_lines).
        rhymeScheme(SonnetConstants.rhymeScheme).build();
    this.setPoemCharacteristic(pc);
  }
}
