import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Myserver
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);	
		String message, reply;

		try
		{
			System.out.println("\n---SERVER----");
			System.out.println("\nWaiting for Client...");

			ServerSocket ss = new ServerSocket(1969);
			Socket connection = ss.accept();
			DataInputStream dis = new DataInputStream(connection.getInputStream());
			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
	
			System.out.println("\nClient Connected");
			
			while(true)
			{
				System.out.println("Waiting for client message");
				reply = dis.readUTF();
				System.out.println("Client Says : " + reply);
				
				if(reply.equalsIgnoreCase("stop"))
				{
					System.out.println("\nClient Stopped");
					dos.flush();
					dos.close();
					dis.close();
					connection.close();
					ss.close();
					return;
				}

				System.out.print("\nEnter Message: ");
				message = sc.nextLine();
				dos.writeUTF(message);
				
				if(message.equalsIgnoreCase("stop"))
				{
					dos.flush();
					dos.close();
					dis.close();
					connection.close();
					ss.close();
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