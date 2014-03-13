// model evaluate, backward and forwards algorithm
package hmm;

public class Evaluate {
	private HMM hm;
	private String strPath;
	/*
	 * implement of forward algorithm 
	 * @param hm: HMM model
	 */
	public double forward(HMM hm,int T,int []O,double[][]alpha){
		int i,j;   /*state indices*/
		int t;     /*time index*/
		double sum;  /*partial sum*/
		double prob=0.0; /*return probability*/
		/*1. Initialization*/
		for(i=0;i<hm.N;i++)
		{
			alpha[0][i]=(hm.pi[i])*(hm.A[i][O[0]]);
		}
		/*2. Induction */
        for (t = 0; t < T-1; t++) {
            for (j = 0; j <hm.N; j++) {
                    sum = 0.0;
                    for (i = 0; i < hm.N; i++)
                            sum += alpha[t][i]* (hm.A[i][j]);

                    alpha[t+1][j] = sum*(hm.B[j][O[t+1]]);
            }
        }
		
		/*3. Termination*/
        prob = 0.0;
        for (i = 1; i <= hm.N; i++)
                prob += alpha[T][i];
        
        return prob;
	}
	/*
	 * implement of backward algorithm
	 * @hm: HMM model
	 * T: time , 1~T
	 */
	public double backwrad(HMM hm,int T,int[]O,double[][]beta ){
		int i,j;  /*state indices*/
		int t;  /*time index*/
		double prob=0.0;
		double sum; /*partial sum*/
		/*1. Initialization*/
		for(i=0;i<hm.N;i++)
			beta[T][i]=1;
		/*2. Induction*/
		for(t=T-1;i>0;t--)
		{
			for(i=0;i<hm.N;i++)
			{
				sum=0.0;
				for(j=0;j<hm.N;j++)
				{
					sum+=(hm.A[i][j])*(hm.B[j][O[t+1]])*(beta[t+1][j]);
				}
			}
		}
		/*3.Termination*/
		for(i=0;i<hm.M;i++)
			prob+=hm.pi[i]*hm.B[i][O[1]]*beta[1][i];
		return prob;
	}
	public static void main(String[]args){
		
	}
}
