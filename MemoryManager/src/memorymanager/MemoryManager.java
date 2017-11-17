package memorymanager;

//Imported the appropriate java libraries for the overall functionality of the memory manager

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Declared a class with an appropriate name to depict the task which would be carried out during runtime
public class MemoryManager {
BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
int process[], rprocess[], block[], rblock[], usage[], rusage[];
int p, b, free, used, rfree, rused, c, c1;


//code for identifying the amount of processes and  
//the available memory blocks and defining the accepted values.
//
public MemoryManager() throws IOException{
System.out.println("Enter number of blocks");
b=Integer.parseInt(input.readLine());
System.out.println("Enter number of processes");
p=Integer.parseInt(input.readLine());
process=new int[p];
rprocess=new int[p];
block=new int[b];
rblock=new int[b];
usage=new int[b];
rusage=new int[b];
c=0;
//TODO Auto-generated constructor stub
}



//code for identifying the sizes of the memory blocks and also the size of the requested process
void read() throws IOException
{
	try{
		
	
int i;
System.out.println("Enter block sizes");
for(i=0;i<b;i++)
{
System.out.print("Block "+(i+1)+" : ");
rblock[i]=Integer.parseInt(input.readLine());
}
System.out.println("For the memory blocks are they already utilized or empty?");
for(i=0;i<b;i++)
{
System.out.println("Is block "+(i+1)+" used (1) or free (0)?");
rusage[i]=Integer.parseInt(input.readLine());
if(rusage[i]==1)
{
rused=rused+rblock[i];
c1++;
}
else
rfree=rfree+rblock[i];
}
System.out.println("Enter process demand");
for(i=0;i<p;i++)
{
System.out.print("Process "+(i+1)+" : ");
rprocess[i]=Integer.parseInt(input.readLine());
}
	}catch(IOException e){
	e.printStackTrace();

}
	
}

void reset()
{
int i;
for(i=0;i<b;i++)
{
block[i]=rblock[i];
usage[i]=rusage[i];
}
for(i=0;i<p;i++)
process[i]=rprocess[i];
used=rused;
free=rfree;
c=c1;
}



//common display method edited to show the information gathered from the completion of the task processes
void display()
{
int total;
total=rused+rfree;
System.out.println("Blocks used = "+c);
System.out.println("Total used space = "+used);
System.out.println("Blocks free = "+(b-c));
System.out.println("Total free space = "+(total-used));
}

//implementation method for worst fit algorithm 
void w_fit()
{
int i,j,size,worst;
for(i=0;i<p;i++)
{
size=0;
worst=-1;
for(j=0;j<b;j++)
{
if(process[i]<=block[j]&&usage[j]==0&&(block[j]-process[i])>size)
{
size=block[j]-process[i];
worst=j;
}
}
if(worst!=-1) //Ensuring a worst fit.
{
usage[worst]=1;
used=used+block[worst];
c++;
System.out.println("Process "+(i+1)+" is in block "+(worst+1));
}
}
}



/**
* @param args
*/
public static void main(String[] args) throws IOException{
	try{
		
	
BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
int option;
String choice;
MemoryManager f=new MemoryManager();
f.read();
do
{
f.reset();
System.out.println("********");
System.out.println("* Menu *");
System.out.println("********");
System.out.println("1. Worst fit");
System.out.println("2. Block information");
System.out.println("Enter option");
option=Integer.parseInt(input.readLine());

switch(option)
{
case 1: f.w_fit();
break;
case 2: f.display();
break;

default: System.out.println("Invalid input");
}
f.display();
System.out.println("Press Y to continue");
choice=input.readLine();
}
while(choice.compareToIgnoreCase("y")==0);
//TODO Auto-generated method stub
}catch(IOException e){
	e.printStackTrace();
}
}
}