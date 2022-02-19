package database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serversocket = new ServerSocket(9000);
            System.out.println("Server says: Server started ... waiting ...");

            while (true)
            {
                Socket socket = serversocket.accept();

                InetAddress inetad = socket.getInetAddress();
                System.out.println("Client IP address: " + inetad.getHostAddress() +
                        " host name: " + inetad.getHostName());

                new Thread(new ProcessSingleClient(socket)).start();
            }
        }

        catch (IOException excep)
        {
            excep.printStackTrace();
        }
    }
}

class ProcessSingleClient implements Runnable
{
    private Socket socket;

    public ProcessSingleClient(Socket s)
    {
        socket = s;
    }

    public void run()
    {
        try
        {
            System.out.println("Thread Id = " + Thread.currentThread().getId() + " start");
            DataInputStream inputfromclient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputtoclient = new DataOutputStream(socket.getOutputStream());

            int j = inputfromclient.readInt();
            System.out.printf("Server says: client sent %d%n", j);

            outputtoclient.writeInt(j * j);
            System.out.printf("Server says: client got back %d%n", j * j);
            System.out.println("Thread Id = " + Thread.currentThread().getId() + " end");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
