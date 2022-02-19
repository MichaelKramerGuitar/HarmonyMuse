package database;

import java.net.*;

public class TestInetAddress
{
    public static void main(String[] args)
    {
        try
        {
            InetAddress inetad = InetAddress.getByName(args[0]);
            System.out.println("IP add = " + inetad.getHostAddress()
                    + " Host name = " + inetad.getHostName());
        }

        catch (UnknownHostException excep)
        {
            System.out.println("Unknown IP address or host " + args[0]);
        }
    }
}
