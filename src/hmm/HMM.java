/*
 * Author wangliang
 * Date: 2014/03/01
 */
package hmm;
public class HMM {
	private int M;     //number of observation symbols
	private int N;     //number of hidden states
	private int [][]transmit;  //transition probability of going from  state i at time t to state j at time t+1 
	private int [][]generate;   // probability of observation symbol k at i
	public HMM(int m,int n){
		this.M=m;
		this.N=n;
	}
	public double forward(){
		return 0;
	}
	public double backward(){
		return 0;
	}
	public double viterbi(){
		return 0;
	}
	public static void main(String[] args){
		System.out.println("----------My HMM-------------");
	}
}
