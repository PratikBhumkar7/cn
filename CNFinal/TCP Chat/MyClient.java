
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class MyClient
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String message, reply;
	
		try
		{
			Socket connection = new Socket("localhost",1969);
			DataInputStream dis = new DataInputStream(connection.getInputStream());
			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			
			System.out.println("\n---CLIENT----");
			System.out.println("\nConnected to Server\n");

			while(true)
			{
				System.out.print("\nEnter Message : ");
				message=sc.nextLine();
				dos.writeUTF(message);

				if(message.equalsIgnoreCase("stop"))
				{
					System.out.println("\nClient Stopped");
					dos.flush();
					dos.close();
					dis.close();
					connection.close();
					return;
				}

				System.out.println("Waiting for server message");
				reply=dis.readUTF();
				System.out.println("Server Says : " + reply);
				
				if(reply.equalsIgnoreCase("stop"))
				{
					System.out.println("\nServer Stopped");
					dos.flush();
					dos.close();
					dis.close();
					connection.close();
					return;
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}	
	}
}
