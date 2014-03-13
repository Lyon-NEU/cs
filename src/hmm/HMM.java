/*
 * Author wangliang
 * Date: 2014/03/01
 */
package hmm;
import java.io.*;
import java.util.*;
public class HMM {
	public int M;     //number of observation symbols
	public int N;     //number of hidden states
	public double [][]A;  //transition probability of going from  state i at time t to state j at time t+1 
	public double [][]B;   // probability of observation symbol k at time i
	public double[]pi;         //initial probability
	public HMM(){

	}
	/*
	 * return number of observation symbols
	 */
	public int getM()
	{
		return M;
	}
	/*
	 * return number of hidden states
	 */
	public int getN()
	{
		return N;
	}
	/*
	 * read model from file
	 * @param filename: file to be read
	 * @parma hm:  save model 
	 * */
	public static void readHMM(String filename,HMM hm){
		BufferedReader in;
		try{
			in=new BufferedReader(new FileReader(new File(filename)));
			String line;	
			line=in.readLine();
			if(line.startsWith("M="))
			{
				hm.M=Integer.parseInt(line.substring(line.indexOf("= ")+2).trim());
			}else{
				System.out.println("data format error!");
				return;
			}
			line=in.readLine();
			if(line.startsWith("N="))
			{
				int index=line.indexOf("= ");
				if(-1!=index)
					hm.N=Integer.parseInt(line.substring(index+2).trim());
			}else{
				System.out.println("data format error!");
				return;
			}
			hm.A=new double[hm.N][hm.N];
			line=in.readLine();
			if(line.startsWith("A"))
			{
				//read transition array
				for(int i=0;i<hm.N;i++)
				{
					line=in.readLine();
					String[] dtmp=line.split(" ");
					for(int j=0;j<dtmp.length;j++)
						hm.A[i][j]=Double.parseDouble(dtmp[j]);
				}
			}else{
				System.out.println("data format error!");
				return;
			}
			hm.B=new double[hm.N][hm.M];
			line=in.readLine();
			if(line.startsWith("B"))
			{
				//read observation probability
				for(int i=0;i<hm.N;i++)
				{
					line=in.readLine();
					String[] dtmp=line.split("\\s+");  //split("") wll save empty string, use regex;
					for(int j=0;j<dtmp.length;j++)
						hm.B[i][j]=Double.parseDouble(dtmp[j]);
				}
			}else{
				System.out.println("data format error!");
				return;
			}
			hm.pi=new double[hm.N];
			line=in.readLine();
			if(line.startsWith("pi"))
			{
				//read initial probability
				line=in.readLine();
				String[]pp=line.split(" ");
				for(int i=0;i<hm.N;i++)
					hm.pi[i]=Double.parseDouble(pp[i]);
			}else{
				System.out.println("data format error!");
				return;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * write HMM to file
	 * @param filename: file to save model
	 * @param hm: model to be write
	 */
	public static void writeHMM(String filename,HMM hm){
		BufferedReader out;
		try{
			out=new BufferedReader(new FileReader(new File(""))); //write model params into file
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//print the model
	public static void printHMM(HMM hm)
	{
		System.out.println("----------------------------------------");
		System.out.println("N(number of hidden state: )"+hm.N);
		System.out.println("Number of observation symbols:"+hm.M);
		System.out.println("transtion array:");
		for(int i=0;i<hm.N;i++)
			for(int j=0;j<hm.N;j++)
				System.out.print("   "+hm.A[i][j]+(j==hm.N-1?'\n':" "));
		System.out.println("\nlaunch array:");
		for(int i=0;i<hm.N;i++)
			for(int j=0;j<hm.M;j++)
				System.out.print("   "+hm.B[i][j]+(j==hm.M-1?'\n':" "));
		System.out.println("Initial Array:");
		for(double pt: hm.pi)
			System.out.print(pt);
		System.out.println("\n----------------------end---------------------------");
	}
	public static void main(String[] args){
		System.out.println("----------A Hidden Markov Model-------------");
		HMM hm=new HMM();
		readHMM("test\\test.hmm",hm);
		printHMM(hm);
	}
}
