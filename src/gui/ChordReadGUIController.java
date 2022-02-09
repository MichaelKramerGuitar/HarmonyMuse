package gui;

import general.containers.Chord;
import builders.ChordSequence;
import commandline.app.CharactersTable;
import commandline.app.CommonView;
import file.handling.ReadFromJSON;

import java.util.stream.Stream;

public class ChordReadGUIController {

    private CharactersTable ctable = new CharactersTable();

    private ReadFromJSON reader = new ReadFromJSON();

    private ChordSequence chordSequence;

    // getters
    public CharactersTable getCTable() {
        return ctable;
    }

    public ChordSequence getChordSequence() {
        return chordSequence;
    }

    public ReadFromJSON getReader() {
        return reader;
    }

    public String analyze(ChordSequence chordSequence){
        Stream<Chord> chordStream = chordSequence.chordSequenceToChordStream(chordSequence);
        chordStream.forEach(i -> System.out.println(i));

        String returnText = "";
        String[] romans = CommonView.addRomanNumeral(chordSequence);
        StringBuilder tonalCenter = CommonView.replaceAccidental(chordSequence.getTonalCenter());
        returnText += "Sequence tonal center: " + tonalCenter.toString().toUpperCase() + "\n";
        for (String roman: romans
        ) {
            //System.out.print(roman + " ");
            returnText += roman + " ";
        }
        return returnText;
    }
}
