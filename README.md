Poetry_Framework
================
Project Members:
1) Arvind Krishnaa J
2) Greg Cobb
3) Sonal Danak
4) Michelle Scott

Package Structure
==================
org.design.CMUDictUtils : Utilities to parse a line of text from the CMU dictionary and write it to the database

org.design.db : Connection to the database - simple DB operations like retrieve, insert etc.,

org.design.fileutils : Utilities to parse files. The corpus-related methods would be here.

org.design.poetryutils : Simple functions like generate words rhyming with a given word, check if two words rhyme (+ stress related methods to be added...) and so on

org.design.primitives : Basic classes used in poems

org.framework.exceptions : Custom exception classes

The following packages contain (will contain) all the classes and helpers which perform the core functionality:
	1. org.framework.assistors
	2. org.framework.generators
	3. org.frmaework.recognizers

org.framewor.interfaces : An interface containing constants + an interface defining all the functions of the poetry framework --> PoetryFrameworkOperations.java (New functions need to be declared here).

org.framework.poemtypes : Contains classes for each category of poem. Each class should implement the methods of the poetry framework interface.

org.design.framework : Driver class which pieces together all the components. This class implements the PoetryFrameworkOperations interface and therefore will implement all the methods.

Important Classes/Interfaces: TO-DO: 1 (All of us)
===================================================

PoetryRecognizer.java
PoetryGenerator.java
PoetryAssistant.java
PoetryFramework.java
-----> All the above are incomplete and need to be fully defined
PoetryFrameworkOperations.java
-----> Any new methods/functionality we think of must go here.

To-do: 2 (Greg)
================

(a) Right now we only have basic rules of poems baked into the abstract class Poem's PoemCharacteristic member's builder function. We need to incorporate rules like
--> For a limerick, lines 3 and 4 have the same number of syllables
and so on. (in a new package maybe?)
(b) We need to identify more basic rules which we can bake into the PoemCharacteristic class itself.

To-do: 3 (Sonal)
==================

Corpus related parsing/tokenizing and so on. 
Stuff should be in the package ord.design.fileutils.

To-do: 4 (Michelle)
====================

Create a new package (org.framework.gui) with the GUI stubs

To-do: 5 (Arvind)
===================

Hook up the Java classes from To-dos 1,2 and 3 to the GUI.

To-do: 6 (All of us)
=====================

To infinity and beyond!
	--> More poem types
	--> Different corpora
	--> etc.,
