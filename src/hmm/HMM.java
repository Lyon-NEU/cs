/*
 * Author wangliang
 * Date: 2014/03/01
 */
package hmm;
import java.io.*;
import java.util.*;
public class HMM {
	private int M;     //number of observation symbols
	private int N;     //number of hidden states
	private int [][]transmit;  //transition probability of going from  state i at time t to state j at time t+1 
	private int [][]generate;   // probability of observation symbol k at time i
	private int[]pi;         //initial probability
	public HMM(int m,int n){
		this.M=m;
		this.N=n;
	}
	/*
	 * read model from file
	 * @param filename: file to be read
	 * @parma hm:  save model 
	 * */
	public void readHMM(String filename,HMM hm){
		BufferedReader in;
		try{
			in=new BufferedReader(new FileReader(new File("")));
			String line;	
			line=in.readLine();
			if(line.startsWith("M="))
			{
				hm.M=Integer.parseInt(line.substring(line.indexOf('='))+1);
			}else{
				System.out.println("data format error!");
				return;
			}
			line=in.readLine();
			if(line.startsWith("N="))
			{
				hm.N=Integer.parseInt(line.substring(line.indexOf('='))+1);
			}else{
				System.out.println("data format error!");
				return;
			}
			line=in.readLine();
			if(line.startsWith("A"))
			{
				//read transition array
				for(int i=0;i<hm.N;i++)
				{
					line=in.readLine();
					String[] dtmp=line.split(" ");
					for(int j=0;j<dtmp.length;j++)
						hm.transmit[i][j]=Integer.parseInt(dtmp[j]);
				}
			}else{
				System.out.println("data format error!");
				return;
			}
			line=in.readLine();
			if(line.startsWith("B"))
			{
				//read observation probability
				for(int i=0;i<hm.N;i++)
				{
					line=in.readLine();
					String[] dtmp=line.split(" ");
					for(int j=0;j<dtmp.length;j++)
						hm.generate[i][j]=Integer.parseInt(dtmp[j]);
				}
			}else{
				System.out.println("data format error!");
				return;
			}
			line=in.readLine();
			if(line.startsWith("pi"))
			{
				//read initial probability
				line=in.readLine();
				String[]pp=line.split(" ");
				for(int i=0;i<hm.N;i++)
					hm.pi[i]=Integer.parseInt(pp[i]);
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
	public void writeHMM(String filename,HMM hm){
		BufferedReader out;
		try{
			out=new BufferedReader(new FileReader(new File(""))); //write model params into file
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//print the model
	public void printHMM(HMM hm)
	{
		System.out.println("----------------------------------------");
		System.out.println("N(number of hidden state: )"+hm.N);
		System.out.println("Number of observation symbols:"+hm.M);
		System.out.println("transtion array:");
		for(int i=0;i<hm.N;i++)
			for(int j=0;j<hm.N;j++)
				System.out.print("   "+hm.generate[i][j]+(j==hm.N-1?' ':"\n"));
		System.out.println("launch array:");
		for(int i=0;i<hm.N;i++)
			for(int j=0;j<hm.M;j++)
				System.out.println("   "+hm.generate[i][j]+(j==hm.M-1?' ':"\n"));
	}
	public static void main(String[] args){
		System.out.println("----------A Hidden Markov Model-------------");
	}
}
