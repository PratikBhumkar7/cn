import java.net.*;
import java.io.*;
public class Server1
{
	public static  void main(String args[]) throws SocketException
	{
		DataOutputStream dos = null;
		DataInputStream dis = null;

		try
		{
			int a[]={30,40,50,60,70,80,90,100};
			ServerSocket s =new ServerSocket(1234);
			System.out.println("Waiting for connection");
			Socket client = s.accept();
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			System.out.println("No of Packet sent is:" +a.length);
			int y = a.length;
			dos.write(y);
			dos.flush();
			for(int i=0;i<a.length;i++)
			{
				dos.write(a[i]);
				dos.flush();
			}
			int k =dis.read();
			dos.write(a[k]);
			dos.flush();

		}
		catch(IOException e)
		{
				System.out.println(e);
		}
		finally
		{
			try
			{
				dos.close();
				dis.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}