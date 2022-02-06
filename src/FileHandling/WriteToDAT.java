package FileHandling;

import Builders.ChordSequence;
import Builders.InvalidNoteException;
import Builders.Note;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.DiminishedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteToDAT {

    public void writeChordSequenceToDAT(String filename, ChordSequence chordSequence){

        try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("data\\" + filename +".dat"))) {
            outfile.writeObject(chordSequence);

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        WriteToDAT writer = new WriteToDAT();
        Note c;
        Note fSharp;
        Note eNat;
        Note a;
        try{
            c = new Note("c");
            MajorTriad C = new MajorTriad(c);
            fSharp = new Note("f#");
            MinorTriad Fsharp = new MinorTriad(fSharp);
            eNat = new Note("e");
            AugmentedTriad E = new AugmentedTriad(eNat);
            a = new Note("a");
            DiminishedTriad A = new DiminishedTriad(a);
            ChordSequence chordSequence = new ChordSequence(C, Fsharp, E, A, c);


        }catch (InvalidNoteException e){
            System.out.println(e);
        }
    }
}
