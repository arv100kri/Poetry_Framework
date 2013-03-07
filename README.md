Poetry_Framework
================
Project Members:
1) Arvind Krishnaa J
2) Greg Cobb
3) Sonal Danak
4) Michelle Scott

Packages Structure
===================
org.design.CMUDictUtils : Utilities to parse a line of text from the CMU dictionary and write it to the database

org.design.db : Connection to the database - simple DB operations like retrieve, insert etc.,

org.design.poetryutils : Simple functions like generate words rhyming with a given word, check if two words rhyme (+ stress related methods to be added...) and so on

org.design.primitives : Basic classes used in poems

org.framework.exceptions

org.framewor.interfaces : An interface containing constants + an interface defining all the functions of the poetry framework

org.framework.poemtypes : !--<Contentious>--! Contains classes for each category of poem. Each class should implement the methods of the poetry framework interface.

org.design.framework : Collection of classes which piece together all the functionality

Contentions
============

I prefer to create a new class for each type of poem and each of these classes will implement the poetry framework functions instead of having one central logic.

Then in the driver class, we can create on-the-fly instances of these classes to perform the operations.
