package FileHandling;

import Builders.ChordSequence;
import Builders.InvalidNoteException;
import Builders.Note;
import ThreeNoteStructures.MajorTriad;

import javax.xml.bind.JAXB;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteToXML {

    public static void main(String[] args) {

        Note c;
        Note fSharp;
        try(BufferedWriter outfile =
                    Files.newBufferedWriter(Paths.get("data\\xml_test.xml"))){

            try {
                c = new Note("c");
                MajorTriad C = new MajorTriad(c);
                System.out.println(C);
                fSharp = new Note("f#");
                MajorTriad Fsharp = new MajorTriad(fSharp);
                System.out.println(Fsharp);
                ChordSequence chordSequence = new ChordSequence(C, Fsharp, c);

                JAXB.marshal(chordSequence, outfile);

            }catch (InvalidNoteException e){
                System.out.println(e);
            }


            }catch (IOException e){
            System.out.println(e);
        }
    }
}
