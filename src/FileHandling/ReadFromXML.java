package FileHandling;

import ThreeNoteStructures.MajorTriad;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromXML {

    public static void main(String[] args) {
        try(
                BufferedReader infile = Files.newBufferedReader(Paths.get("data\\xml_test.xml"))){
            MajorTriad chord = JAXB.unmarshal(infile, MajorTriad.class);
            System.out.println(chord);
        }catch(IOException e){
            System.out.println(e);
        }
    }

}

