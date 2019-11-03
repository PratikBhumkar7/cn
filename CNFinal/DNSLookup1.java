import java.net.*;
import java.util.Scanner;
public class DNSLookup1
{

	public void getIPfromURL(String str)
	{
		try
		{	InetAddress in =InetAddress.getByName(new URL(str).getHost());
			System.out.println("IP :"  +in.getHostAddress());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void getHostnameformIP(String str)
	{
		try
		{
			InetAddress inet = InetAddress.getByName(str);
			String Hostname = inet.getHostName();
			String canonicalHostname= inet.getCanonicalHostName();
			System.out.println("HostName :" +Hostname);
			System.out.println("canonical hostname :"+canonicalHostname);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void main(String args[])
	{
		int ch;
		String str;
		Scanner sc = new Scanner(System.in);
		DNSLookup1 obj = new DNSLookup1();
		do
		{
			System.out.println("1.IP Address from URL \n2. Hostname from IP Address \n3.Exit\nEnter your choice");
			ch=Integer.parseInt(sc.nextLine());
			switch(ch)
			{
				case 1:
						System.out.println("Enter the URL");
						str=sc.nextLine();
						obj.getIPfromURL(str);
						break;
				case 2:
						System.out.println("Enter the IP Address");
						str = sc.nextLine();
						obj.getHostnameformIP(str);
						break;

			}
		}
		while(ch<3);
	}
}