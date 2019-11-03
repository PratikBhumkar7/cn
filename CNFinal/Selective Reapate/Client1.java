import java.net.*;
import java.io.*;
public class Client1
{
	static Socket s;
	public static void main(String args[]) throws SocketException
	{
		try{
		int v[]= new int[8];
		int n=0;
		InetAddress addr = InetAddress.getByName("LocalHost");
		System.out.println(addr);
		s = new Socket(addr,1234);
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		int p = in.read();
		System.out.println("\nNo of Frame is:" +p);

		for(int i=0;i<p;i++)
		{
			v[i]=in.read();
			System.out.println(v[i]);
		}

		v[5]=-1;
		for(int i=0;i<p;i++)
		{ 
			System.out.println("Recived frame is :"+v[i]);
		}

		for(int i=0;i<p;i++)
		{
			if(v[i]==-1)
			{
				System.out.println("Request to Retransmit from packet no" +(i+1)+ "Again");
				n=i;
				out.write(n);
				out.flush();
			}
		}
		System.out.println();

		v[n]=in.read();
		System.out.println("Recived frame is" +v[n]);

	}catch(IOException e)
	{
		System.out.println(e);
	}
	}
}