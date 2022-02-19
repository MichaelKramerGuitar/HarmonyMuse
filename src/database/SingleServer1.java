package database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleServer1
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serversocket = new ServerSocket(9000);
            System.out.println("Server says: Server started … waiting …");
            Socket socket = serversocket.accept();

            DataInputStream inputfromclient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputtoclient = new DataOutputStream(socket.getOutputStream());

            int j = inputfromclient.readInt();
            System.out.printf("Server says: client sent %d%n", j);

            outputtoclient.writeInt(j * j);
            System.out.printf("Server says: client got back %d%n", j * j);

        }

        catch (IOException excep)
        {
            excep.printStackTrace();
        }
    }
}