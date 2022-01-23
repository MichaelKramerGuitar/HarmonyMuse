package HarmonyMuse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;


public class WriteToFile {

    public static void clearFile(){
        try {
            File file = new File("data\\input.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.flush();
        }catch (IOException e){
            System.out.println("Error flushing file");
            e.printStackTrace();
        }

    }
    public static void writeToFile(String[] rawInput){

        try{
            File file = new File("data\\input.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            //BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Formatter outfile = new Formatter(fileWriter);
            for(int i = 0; i < rawInput.length; i++){
                System.out.println(rawInput[i]);
                outfile.format("%s ", rawInput[i]);
                //bufferedWriter.write(rawInput[i]);
            }
            outfile.format("%n");
            outfile.close();
            //bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }
}
