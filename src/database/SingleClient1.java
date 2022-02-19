package database;

import java.io.*;
import java.net.*;
import java.util.*;

public class SingleClient1
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Client says: client started");
            Socket socket = new Socket("localhost", 9000);

            DataInputStream fromserver = new DataInputStream(socket.getInputStream());
            DataOutputStream toserver = new DataOutputStream(socket.getOutputStream());

            Scanner input = new Scanner(System.in);
            System.out.print("Enter an integer that you want squared: ");
            int i = input.nextInt();

            System.out.printf("Client says: client passed %d to server%n", i);
            toserver.writeInt(i);
            int j = fromserver.readInt();
            System.out.printf("Client says: client received %d as the square from server%n", j);
        }

        catch (IOException excep)
        {
            excep.printStackTrace();
        }
    }
}