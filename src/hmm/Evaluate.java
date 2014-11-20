// model evaluate, backward and forwards algorithm
package hmm;
import java.io.*;
import java.util.*;
public class Evaluate {
	private HMM hm;
	private String strPath;
	/*
	 * implement of forward algorithm 
	 * @param hm: HMM model
	 */
	public static double forward(HMM hm,int T,Integer []O,double[][]alpha){
		int i,j;   /*state indices*/
		int t;     /*time index*/
		double sum;  /*partial sum*/
		double prob=0.0; /*return probability*/
		/*1. Initialization*/
		for(i=0;i<hm.getN();i++)
		{
			alpha[0][i]=(hm.pi[i])*(hm.A[i][O[0]]);
		}
		/*2. Induction */
        for (t = 0; t < T-1; t++) {
            for (j = 0; j <hm.getN(); j++) {
                    sum = 0.0;
                    for (i = 0; i < hm.getN(); i++)
                            sum += alpha[t][i]* (hm.A[i][j]);

                    alpha[t+1][j] = sum*(hm.B[j][O[t+1]-1]);
            }
        }
		
		/*3. Termination*/
        prob = 0.0;
        for (i = 1; i <= hm.getN(); i++)
                prob += alpha[T][i];
        
        return prob;
	}
	/*
	 * implement of backward algorithm
	 * @hm: HMM model
	 * T: time , 1~T
	 */
	public static double backwrad(HMM hm,int T,int[]O,double[][]beta ){
		int i,j;  /*state indices*/
		int t;  /*time index*/
		double prob=0.0;
		double sum; /*partial sum*/
		/*1. Initialization*/
		for(i=0;i<hm.getN();i++)
			beta[T][i]=1;
		/*2. Induction*/
		for(t=T-1;i>0;t--)
		{
			for(i=0;i<hm.getN();i++)
			{
				sum=0.0;
				for(j=0;j<hm.getN();j++)
				{
					sum+=(hm.A[i][j])*(hm.B[j][O[t+1]])*(beta[t+1][j]);
				}
			}
		}
		/*3.Termination*/
		for(i=0;i<hm.getM();i++)
			prob+=hm.pi[i]*hm.B[i][O[1]]*beta[1][i];
		return prob;
	}
	public static void main(String[]args){
		/*1. read model from file*/
		HMM test=new HMM();
		test.readHMM("F:\\workspace\\HMM\\test\\test.HMM");
		/*2. read sequences*/
		/*BufferedReader in;
		String path=System.getProperty("user.dir");
		try{
			
			in=new BufferedReader(new FileReader(new File("")));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}*/
		System.out.println("Please input test sequences:");
		Scanner in=new Scanner(System.in);
		String[]seq=in.nextLine().split(" ");
		Integer []O=new Integer[seq.length];
		for(int i=0;i<seq.length;i++)
			O[i]=Integer.parseInt(seq[i]);
		/*3. cal probability*/
		double [][]alpha=new double[seq.length][test.getN()];
		double prob=0.0;
		prob=forward(test,seq.length,O,alpha);
		
		/**/
		System.out.printf("forward probability: %f",prob);
	}
}
