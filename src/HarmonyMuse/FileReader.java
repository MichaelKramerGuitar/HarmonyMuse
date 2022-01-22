package HarmonyMuse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        // open input.txt, read its contents and close the file
        try(Scanner input = new Scanner(Paths.get("data\\input.txt"))) {
            System.out.printf("%-10s%-10s%-10s%n", "Note 1",
                    "Note2", "Note3");

            // read record from file
            while (input.hasNext()) { // while there is more to read
                // display record contents
                System.out.printf("%-10s%-10s%-10s%n",
                        input.next(), input.next(), input.next());
            }
        } // try(with recources IMPLICITLY calls input.close()
        catch (IOException | NoSuchElementException |
                IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
