import java.io.*;
import java.net.*;
import java.util.*;

public class tserver
{
	public static void main(String args[]) throws IOException
	{
		int noOfFrames, mode;

		System.out.println("----SERVER----");
		System.out.println("Waiting for connections: ");

		InetAddress ip = InetAddress.getByName("Localhost");
		ServerSocket ss = new ServerSocket(500);
		Socket connection = new Socket();
		connection = ss.accept();
		BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		
		System.out.println("Client Connected.");
		noOfFrames = in.read();
		boolean frames[] = new boolean[noOfFrames];

		mode = in.read();

		if(mode == 0)
		{
			for(int i = 1; i <= noOfFrames; i++)
			{
				out.write(i);
				out.flush();
				System.out.println("\nFrame " + i + " sent");
				try {
					Thread.sleep(2000);
				}
				catch(Exception e) {
					System.out.println(e);
				}
				int temp = in.read();
				System.out.println("Acknoledgement recived for frame " + temp);
			}
		}

		if(mode == 1)
		{
			for(int i = 1; i <= noOfFrames; i++)
			{

				if(i == 2)
				{
					System.out.println("\nFrame " + i + " sent.");
				}
				else
				{
					out.write(i);
					out.flush();
					System.out.println("\nFrame " + i + " sent.");
					try{
						Thread.sleep(2000);
					}
					catch(Exception e){
						System.out.println(e);
					}
					int a = in.read();
					if(a != 255)
					{
						frames[i-1] = true;
						System.out.println("Acknoledgement for frame " + a + " recived");
					}
					else
						frames[i-1] = false;
				}
			}

			for(int i = 1; i <= noOfFrames; i++)
			{
				if(frames[i-1] == false)
				{
					out.write(i);
					out.flush();
					System.out.println("\nResending frame: " + i);
					try{
						Thread.sleep(2000);
					}
					catch(Exception e){
						System.out.println(e);
					}
					int a = in.read();
					System.out.println("Acknoledgement for frame " + i + " recived.");
				}
			}
		}
	}
}