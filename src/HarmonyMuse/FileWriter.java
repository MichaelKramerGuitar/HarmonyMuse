package HarmonyMuse;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileWriter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.printf("%s%n%s%n? ",
                "Enter notes in chord as letters >>> use \"-\" for flats and \"#\" for sharps --->>>",
                "Enter end-of-file indicator to end input.");

        // open clients.txt, output data to the file then close clients.txt
        try (Formatter output = new Formatter("data\\input.txt")) { // try with resources
            while (input.hasNext()) { // loop until end-of-file indicator
                try {
                    // output new record to file; assumes valid input
                    output.format("%s %s %s%n", input.next(), input.next(), input.next());
                }
                catch (NoSuchElementException elementException) {
                    System.err.println("Invalid input. Please try again.");
                    input.nextLine(); // discard input so user can try again
                }

                System.out.print("? ");
            }
        }
        catch (SecurityException | FileNotFoundException |
                FormatterClosedException e) {
            e.printStackTrace();
            System.exit(1); // terminate the program
        }
    }
}
