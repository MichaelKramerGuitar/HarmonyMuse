##CS622 Spring 1 Advanced Programming Techniques Project

**Current functionality is shown by running one of two controllers**
* For Chord entry by individual notes run `CommandLineApp.MainChordEntry.java`
* For Sequence entry (multiple chords) and sequence Roman Numeral Harmonic Analysis _**Assignment 3**_ run `CommandLindApp.MainQuickEntry`
  * See document `metcs622_Assignment2_mgkramer.docx` in assignments dirctory for detailed project description 

_All other classes containing main functions are for debugging purposes as this is a work in progress
and are not intended for public use, however these can be run to see isolated class functionality_

###Note on case representation of musical data as Strings
* Notes are lower case letters 
* Chords are upper case letters
* Roman Numerals 
  * Upper case indicates a Major 3rd 
  * Lower case indicates Minor 3rd 

###All tests are runable
* _**Current Testing Coverage**_
  * Note constructor 
  * Comprehensive Interval construction
    * The Interval class is key to the vision for this project
  * Chord Builder constructor 
  * TriadClassifier
  * TriadFactory (via ThreeNoteStructures)
  * FileHandling 



####Examples of Interval Calculations
_The below examples illustrate the non-trivial task of calculating intervals in a compression function (i.e. everything analyzed within one octave)_

        /**
         *     example1:
         *     bottomNote = e-
         *     bottomNote.intValue = 3
         *     topNote = g
         *     topNote.intValue = 7
         *     interval = 7 - 3
         *     this is correct, 4 half steps are in a Major Third
         *
         *     example2:
         *     bottomNote = a
         *     bottomNote.intValue = 9
         *     topNote = c#
         *     topNote.intValue = 1
         *     interval = (12 - 9) + 1
         *     this is correct, 4 half steps are in a Major Third
         *
         *     example3:
         *     bottomNote = gb
         *     bottomNote.intValue = 6
         *     topNote = f
         *     topNote.intValue = 5
         *     interval = (12 - 6) + 5
         *     this is correct, 11 half steps are in a Major Seventh
         */