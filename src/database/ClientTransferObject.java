package database;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientTransferObject
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Client says: client started");
            Socket socket = new Socket("localhost", 9000);

            ObjectOutputStream toserver = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromserver = new ObjectInputStream(socket.getInputStream());

            Scanner input = new Scanner(System.in);
            System.out.print("Enter employee id: ");
            int id = input.nextInt();
            System.out.print("Enter salary: ");
            double salary = input.nextDouble();
            Employee emp = new Employee(id, salary);

            System.out.printf("Client says: client passing employee to server: %s%n", emp);
            toserver.writeObject(emp);
            Object obj = fromserver.readObject();
            if (obj instanceof Employee)
                System.out.printf("Client says: client received revised employee from server: %s%n", (Employee) obj);
            else  System.out.printf("Client says: Something wrong! client received back a non-employee%n");
        }

        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}


class Employee implements Serializable
{
    private int id;
    private double salary;

    public Employee(int empid, double sal)
    {
        id = empid;
        salary = sal;
    }

    public void addbonus()
    {
        salary *= 1.1;  // 10% bonus added
    }

    public String toString()
    {
        return "id = " + id + " and salary = " + salary;
    }
}
