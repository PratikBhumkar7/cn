#include<iostream>
using namespace std;
int main()
{
	int i,j,k,l;
	int frame[20],genrator[20],tempArray[20],crc[20],transmittedArray[20];
	int frameSize,genratorSize,zeros;

	cout<<"\nEnter the frame size: ";
	cin>>frameSize;

	cout<<"Enter the frame: ";
	for(i=0;i<frameSize;i++)
	{
		cin>>frame[i];
	}
	
	cout<<"Enter the genrator size: ";
	cin>>genratorSize;
	
	cout<<"\nEnter the genrator: ";
	for(i=0;i<genratorSize;i++)
	{
		cin>>genrator[i];
	}

	cout<<"\nframe: ";
	for(i=0;i<frameSize;i++)
	{
		cout<<frame[i];
	}
	
	cout<<"\ngenrator: ";
	for(i=0;i<genratorSize;i++)
	{
		cout<<genrator[i];
	}
	zeros= genratorSize-1;
	cout<<"\nCount the number of zeros to be appended: "<<zeros;
	for(i=frameSize;i<frameSize+zeros;i++)
	{
		frame[i]=0;
	}
	for(i=0;i<20;i++)
	{
		tempArray[i]=frame[i];
	}
	cout<<"\nFrame after appending zeros: ";
	for(i=0;i<frameSize+zeros;i++)
	{
		cout<<tempArray[i];
	}
	//division
	for(i=0;i<frameSize;i++)
	{
		j=0;
		k=i;
		if(tempArray[k]>=genrator[j])
		{
			for(k=i,j=0;j<genratorSize;j++,k++)
			{
				if(tempArray[k]==genrator[j])
				tempArray[k]=0;
				else
				tempArray[k]=1;
			}
		}
	}
	for(i=frameSize,j=0;j<genratorSize-1;i++,j++)
	{
		crc[j]=tempArray[i];
	}	
	cout<<"\nCRC :";
	for(i=0;i<genratorSize-1;i++)
	{
		cout<<crc[i];
	}

	for(i=0;i<frameSize;i++)
	{
		tempArray[i]=frame[i];
	}
	for(i=frameSize,j=0;j<genratorSize-1;i++,j++)
	{
		tempArray[i]=crc[j];
	}
	cout<<"\nTransmitted frame: ";
	for(i=0;i<frameSize+genratorSize-1;i++)
	{
		cout<<tempArray[i];
	}
	for(i=0;i<frameSize+genratorSize-1;i++)
	{
		j=0;	
		k=i;
		if(tempArray[k]>=genrator[j])
		{
			for(k=i,j=0;j<genratorSize-1;k++,j++)
			{
				if(tempArray[k]==genrator[j])	
					tempArray[k]=0;
				else
					tempArray[k]=1;
			}
		}
	}
	cout<<"\nRemainder: ";
	for(i=frameSize;i<frameSize+genratorSize-1;i++)
	{
		cout<<tempArray[i];
	}
	int flag=0;
	for(i=frameSize;i<frameSize+genratorSize-1;i++)
	{
		if(tempArray[i]==1)
		flag=1;
	}
	if(flag==0)
	{
		cout<<"\n\nSince the remainder is 0 the msg trasmitted is correct:";
	}
	else
	{
		cout<<"\n\nSince the remainder is not 0 the msg trasmitted is incorrect\n:";
	}


	return 0;
}



