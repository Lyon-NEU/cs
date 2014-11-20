// implement of viterbi algorithm 
package hmm;

public class Decode {
	/**
	 * 
	 * @param phmm
	 * @param T
	 * @param O
	 * @param deta
	 * @param psi
	 * @param prob
	 */
	public void Viterbi(HMM phmm,int T, int[]O,double[][]deta,int [][]psi,int[]q,double prob){
		int i,j;  //state index
		int t;    //time slice
		double maxval,val;
		int maxvalind = 0;
		
		/*1.Initialization*/
		for (i = 0; i < phmm.getN(); i++) {
			deta[0][i]=phmm.B[i][O[0]];
			psi[0][i]=0;
		}
		/*2.Induction*/
		for (t = 1; t <phmm.getN() ; t++) {
			for(j=0;j<phmm.getN();j++){
				maxval=i;
				val=0.0;
				for (i = 0; i < phmm.getN(); i++) {
					val=deta[t-1][i]*(phmm.A[i][j]);
					if (val>maxval) {
						maxval=val;
						maxvalind=i;
					}
				}
				deta[t][j]=maxval*(phmm.B[j][O[t]]);
				psi[t][j]=maxvalind;
			}

		}
		/*3.Termination*/
		prob=0;
		q[T-1]=1;
		for (i = 0; i < psi.length; i++) {
			if (deta[T-1][i]>prob) {
				prob=deta[T-1][i];
				q[T-1]=i;
			}
		}
		/*Find the optimize solution*/
		for (t=T-2; t >=0; t--) {
			q[t]=psi[t+1][O[t+1]];
		}
	}
	public static void main(String []args){
		if(args.length<1){
			System.out.println("Please input parameters!");
		}
	}
}
