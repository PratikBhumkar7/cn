import java.io.*;
import java.net.*;
import java.util.*;

public class tclient
{
	public static void main(String args []) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		int noOfFrames, mode;

		InetAddress address = InetAddress.getByName("Localhost");
		Socket connection = new Socket(address, 500);
		BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		System.out.println("----CLIENT----");
		System.out.println("Connected.......");
		System.out.print("\nEnter the number of frames to be recieved: ");
		noOfFrames = scanner.nextInt();
		out.write(noOfFrames);
		out.flush();
		System.out.println("\nTransmission Mode: ");
		System.out.println("Enter 0 for without error or 1 for with error:");
		mode = scanner.nextInt();
		out.write(mode);

		if(mode == 0)
		{
			for(int i = 1; i <= noOfFrames; i++)
			{
				int temp = in.read();
				System.out.println("\nFrame " + temp + " recived.");
				out.write(temp);
				out.flush();
				System.out.println("Sending acknoledgement for frame " + temp);
			}
		}

		if(mode == 1)
		{
			int check = 1;

			for(int j = 1; j <= noOfFrames; j++)
			{
				int temp = in.read();
				if(temp == check)
				{
					System.out.println("\nFrame " + temp + " recived");
					System.out.println("Sending POSITIVE acknoledgement for frame: " + temp);
					out.write(temp);
					out.flush();
					check++;
				}
				else
				{
					System.out.println("Frame " + temp + " discarded");
					System.out.println("Sending NEGATIVE acknoledgement for frame: " + temp);
					out.write(-1);
					--j;
					System.out.println("J: " + j);
				}
			}
		}
	}
}