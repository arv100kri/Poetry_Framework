package org.design.primitives;

import java.util.List;

/*
 * Defines the characteristics of a poem. One or more of these properties may be empty.
 */
public class PoemCharacteristic
{
	private PoemCharacteristic(PoemCharacteristicBuilder pb)
	{
		//Builder is used to assign variables
		this.number_of_lines = pb.number_of_lines;
		this.number_of_syllables = pb.number_of_syllables;
		this.syllable_count_order = pb.syllable_count_order;
		this.rhymeScheme = pb.rhymeScheme;
	}

	private int number_of_lines;
	private List<Integer> syllable_count_order;
	private int number_of_syllables;
	private List<String> rhymeScheme;

	public static class PoemCharacteristicBuilder
	{
		private int number_of_lines;
		private List<Integer> syllable_count_order;
		private int number_of_syllables;
		private List<String> rhymeScheme;

		public PoemCharacteristicBuilder number_of_lines(int n)
		{
			this.number_of_lines = n;
			return this;
		}

		public PoemCharacteristicBuilder syllable_count_order(List<Integer> order)
		{
			this.syllable_count_order = order;
			return this;
		}

		public PoemCharacteristicBuilder number_of_syllables(int n)
		{
			this.number_of_syllables = n;
			return this;
		}

		public PoemCharacteristicBuilder rhymeScheme(List<String> scheme)
		{
			this.rhymeScheme = scheme;
			return this;
		}

		public PoemCharacteristic build()
		{
			return new PoemCharacteristic(this);
		}
	}

	@Override
	public String toString() {
		return "PoemCharacteristic [number_of_lines=" + number_of_lines
				+ ", syllable_count_order=" + syllable_count_order
				+ ", number_of_syllables=" + number_of_syllables
				+ ", rhymeScheme=" + rhymeScheme + "]";
	}

/* Example of how to create a PoemCharacteristic
	public static void main(String [] args)
	{
		PoemCharacteristic p = new PoemCharacteristic.PoemCharacteristicBuilder().number_of_lines(10).build();
		System.out.println(p);
	}
*/
}
