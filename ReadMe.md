# CS622 Spring 1 Advanced Programming Techniques Project

## Description 

_HarmonyMuse_ is a software for musical harmonic analysis

## Dependencies 
* Java 8
* gson 2.8.5
* javafx sdk 17.0.1
* jetbrains annotations 16.0.2
* junit-jupiter 5.5.2

## How to Run
**Current functionality is shown by running one of the following**
* For ChordSequence entry in the gui run `gui.ChordSequenceEntryPage.java`
* To read and receive analysis of previously entered ChordSequences run `gui.ReadSequencePage.java`
  * Current functionality only supports classification and analysis of Triads _any inversion_
    * The intent is to greatly expand this 
* `TODO` implement an accounts concept 
  * `gui.PublicLibraryEntryPageAdmin.java` allows an Admin account to input song data in the public library `data/concurrencyLib`
* For Chord entry by individual notes run `commandline.app.MainChordEntry.java`
* For Sequence entry (multiple chords) and sequence Roman Numeral Harmonic Analysis _**Assignment 3**_ run `CommandLindApp.MainQuickEntry`
  * See document `assignments/metcs622_Assignment<highestInt>_mgkramer.docx` in assignments dirctory for detailed project description 

_All other classes containing main functions are for debugging purposes as this is a work in progress
and are not intended for public use, however these can be run to see isolated class functionality_

### Current System Classification ability
* All triad qualities and inversions in closed position
  * TODO
    * Open position triads
    * Seventh Chords
    * Extended Tertian (i.e. third based) harmony
    * Potentially integrate with JMusic API https://sourceforge.net/projects/jmusic/

### Note on dead code
* This project is still _**VERY MUCH**_ a work in progress
  * An effort is made on each _CS622_ submission to label with `TODO` where appropriate
    * Otherwise, please consider the above point implied if dead code or unused classes are encountered 
    * _EXAMPLE_ the `file.handling.WriteToDAT.java` and `file.handling.ReadFromDAT.java` files are not currently in use other than tests but pertain directly to Assignment 4 and may be useful in the future


### Note on case representation of musical data as Strings
* Notes are lower case letters 
* Chords are upper case letters
* Roman Numerals 
  * Upper case indicates a Major 3rd 
  * Lower case indicates Minor 3rd 

### All tests are runable
* _**Current Testing Coverage**_
  * Note constructor 
  * Comprehensive Interval construction
    * The Interval class is key to the vision for this project
  * Chord Builder constructor 
  * TriadClassifier
  * TriadFactory (via three.note.structures)
  * FileHandling 
  * Library/Database entry and query



#### Examples of Interval Calculations
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