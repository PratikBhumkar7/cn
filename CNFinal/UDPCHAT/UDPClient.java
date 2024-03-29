import java.io.*;
import java.net.*;
public class UDPClient
{
	public static DatagramSocket cs;
	public static DatagramPacket dp;
	public static BufferedReader dis;
	public static InetAddress ia;
	public static byte buf[]=new byte[1024];
	public static int cport=789,sport=790;
	public static void main(String[] args) throws Exception
	{
		cs=new DatagramSocket(cport);
		dp=new DatagramPacket(buf,buf.length);
		dis=new BufferedReader(new InputStreamReader(System.in));
		ia=InetAddress.getLocalHost();
		System.out.println("Client is running... Type 'Stop' to quit");
		while(true)
		{
			String str=new String(dis.readLine());
			buf=str.getBytes();
			if(str.equalsIgnoreCase("STOP"))
			{
				System.out.println("Terminated...");
				cs.send(new DatagramPacket(buf,str.length(),ia,sport));
				break;
			}
			cs.send(new DatagramPacket(buf,str.length(),ia,sport));
			cs.receive(dp);
			String str2=new String(dp.getData(),0,dp.getLength());
			System.out.println("Server : "+str2);
		}
	}
}
