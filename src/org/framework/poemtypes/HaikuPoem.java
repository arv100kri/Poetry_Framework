package org.framework.poemtypes;

import java.util.List;

import org.design.primitives.Poem;
import org.framework.interfaces.PoetryFrameworkOperations;

public class HaikuPoem extends Poem implements PoetryFrameworkOperations
{

	@Override
	public List<String> generatePoetryFromTitle(String title) {
		// TODO Given a title, generate a Haiku poem for it? --> How?
		return null;
	}

	@Override
	public List<String> generatePoetryFromCorpus(String corpusFileName) {
		// TODO Generate poetry from a large corpus. It may/may not be related to the corpus at all! --> Needs discussion
		return null;
	}

	@Override
	public List<String> generatePoetry() {
		// TODO Generate a random Haiku poem
		return null;
	}

	@Override
	public boolean isValidPoetry(List<String> poem) {
		// TODO Check if the given input is a valid Haiku poem
		return false;
	}

	@Override
	public Poem generatePoetrySkeleton() {
		// TODO Return a PoemSkeleton object, partially initializing its poem with a HaikuPoem object (except the lines)
		// and adding a hints list which provides corresponding hints to each line
		return null;
	}

	@Override
	public List<Poem> identifyPoetryFromCorpus(String corpusFileName) {
		// TODO From the corpus, search to see if there are any Haiku poems in it. If yes, return all of them
		return null;
	}

}
