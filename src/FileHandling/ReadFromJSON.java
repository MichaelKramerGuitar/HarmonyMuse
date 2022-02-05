package FileHandling;

import AbstractStructures.Chord;
import Builders.ChordSequence;
import Classifiers.TriadClassifier;
import ThreeNoteStructures.ConcreteTriad;
import ThreeNoteStructures.MajorTriad;
import ThreeNoteStructures.MinorTriad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class ReadFromJSON<T extends Chord> {

    public void readJSON(String filename){
        TriadClassifier tc = new TriadClassifier();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // you have to give it a concrete type
            Type type = new TypeToken<ChordSequence<ConcreteTriad>>(){}.getType();
            ChordSequence<Chord> deserializedSequence = gson.fromJson(new FileReader("data\\" + filename + ".json"), type);
            for (int i = 0; i < deserializedSequence.getSize(); i++){
                Assertions.assertTrue(deserializedSequence.getChord(i) instanceof ConcreteTriad);
                if(deserializedSequence.getChord(i).getQuality() == tc.getTriadQualities()[2]){
                    MajorTriad majorTriad = (MajorTriad) deserializedSequence.getChord(i);
                    Assertions.assertTrue(deserializedSequence.getChord(i) instanceof MajorTriad);
                    System.out.println("after cast: " + majorTriad);
                }
                else if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[1])){
                    MinorTriad minorTriad = new MinorTriad(deserializedSequence.getChord(i).getRoot());
                    Assertions.assertTrue(minorTriad instanceof MinorTriad);
                    System.out.println("after cast: " + minorTriad);
                }

            }

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TriadClassifier tc = new TriadClassifier();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // you have to give it a concrete type
            Type type = new TypeToken<ChordSequence<ConcreteTriad>>(){}.getType();
            ChordSequence<Chord> deserializedSequence = gson.fromJson(new FileReader("data\\test.json"), type);
            for (int i = 0; i < deserializedSequence.getSize(); i++){
                Assertions.assertTrue(deserializedSequence.getChord(i) instanceof ConcreteTriad);
                if(deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[2])){ // major
                    MajorTriad majorTriad = new MajorTriad(deserializedSequence.getChord(i).getRoot());
                    Assertions.assertTrue(majorTriad instanceof MajorTriad);
                    System.out.println("after cast: " + majorTriad);
                }
                else if (deserializedSequence.getChord(i).getQuality().equals(tc.getTriadQualities()[1])){
                    MinorTriad minorTriad = new MinorTriad(deserializedSequence.getChord(i).getRoot());
                    Assertions.assertTrue(minorTriad instanceof MinorTriad);
                    System.out.println("after cast: " + minorTriad);
                }

            }

        }catch (IOException e){
            System.out.println(e);
        }

    }
}
