package FileHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFromDAT {

    public void readChordSequenceFromDAT(String filename){
        try (ObjectInputStream infile = new ObjectInputStream(new FileInputStream("data\\" + filename +".dat"))){

            for(int i = 0; i < 4; i++){
                System.out.printf("%s%n", (infile.readObject()));
            }
        } catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
}
