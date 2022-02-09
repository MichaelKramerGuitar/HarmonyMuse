package file.handling;

import builders.ChordSequence;
import builders.InvalidNoteException;
import builders.Note;
import three.note.structures.AugmentedTriad;
import three.note.structures.DiminishedTriad;
import three.note.structures.MajorTriad;
import three.note.structures.MinorTriad;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to implement the capability of writing to .dat
 * files
 */
public class WriteToDAT {

    /**
     * The purpose of this method is to Serialize a ChordSequence to a .dat file
     * <p>Precondition: a valid file name and ChordSequence have been entered</p>
     * <p>Postcondition: The ChordSequence is serialized to filename.dat in
     * the specified location</p>
     * @param chordSequence a valid ChordSequence
     * @param filename a valid String filename
     */
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
