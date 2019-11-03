#include<iostream>
using namespace std;

int main()
{
	int data[10],rdata[10];
	int i,p,p1,p2,p4;
	
	//Accept data
	cout<<"Enter the 4 bit data: ";
	cin>>data[0];
	cin>>data[1];
	cin>>data[2];
	cin>>data[4];

	//calculate parity
	data[6]=data[4]^data[2]^data[0];
	data[5]=data[4]^data[1]^data[0];
	data[3]=data[2]^data[1]^data[0];
	
	//display data
	cout<<"\nEncoded data : ";
	for(i=0;i<7;i++)
	{
		cout<<data[i]<<" ";
	}
	cout<<"\nEnter the recived data: ";
	for(i=0;i<7;i++)
	{
		cin>>rdata[i];
	}	
	p1=rdata[6]^rdata[4]^rdata[2]^rdata[0];
	p2=rdata[5]^rdata[4]^rdata[1]^rdata[0];
	p4=rdata[3]^rdata[2]^rdata[1]^rdata[0];
	
	p = p4*4+p2*2+p1;
	
	if(p==0)
	{
		cout<<"\n\nNo Error :";	
	}
	else
	{	
		cout<<"\n\nError at bit :"<<p;
		if(rdata[7-p]==0)
			rdata[7-p]=1;
		else
			rdata[7-p]=0;

		cout<<"\n\nCorrect code is: ";
		for(i=0;i<7;i++)
		{
			cout<<rdata[i];
		}	
		cout<<"\n\n";
	
	}

		


	return 0;
}
