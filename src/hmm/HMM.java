/*
 * Author wangliang
 * Date: 2014/03/01
 */
package hmm;
public class HMM {
	private int M;   
	private int N;
	private int transmit[][]=new int[N][N];
	private int generate[][]=new int[N][M];
	public HMM(int m,int n){
		this.M=m;
		this.N=n;
	}
	public static void main(String[] args){
		System.out.println("----------My HMM-------------");
	}
}
