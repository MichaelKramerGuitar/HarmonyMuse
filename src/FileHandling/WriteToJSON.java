package FileHandling;


import Builders.ChordSequence;
import Builders.InvalidNoteException;
import Builders.Note;
import ThreeNoteStructures.AugmentedTriad;
import ThreeNoteStructures.DiminishedTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteToJSON {

    public static void main(String[] args) {

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

            try (Writer writer = new FileWriter("data\\test.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(chordSequence, writer);
            }catch (IOException | NullPointerException e){
                System.out.println(e);
            }
        }catch (InvalidNoteException e){
            System.out.println(e);
        }


    }
}
