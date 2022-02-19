package database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTransferObject
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serversocket = new ServerSocket(9000);
            System.out.println("Server says: Server started … waiting …");
            Socket socket = serversocket.accept();

            ObjectInputStream inputfromclient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputtoclient = new ObjectOutputStream(socket.getOutputStream());

            Object obj = inputfromclient.readObject();
            if (obj instanceof Employee)
            {
                Employee em = (Employee) obj;
                System.out.printf("Server says: client sent this object: %s%n", em);
                em.addbonus();
                outputtoclient.writeObject(em);
            }
            else
            {
                System.out.printf("Server says: client sent a non-employee - returned to client%n");
                outputtoclient.writeObject(obj);
            }
        }

        catch (ClassNotFoundException excep)
        {
            excep.printStackTrace();
        }

        catch (IOException excep)
        {
            excep.printStackTrace();
        }
    }
}

