import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
    	
    	for (int t = 1; t <= 10; t++) {
    		int T = sc.nextInt();
    		int N = sc.nextInt();
    		int M = sc.nextInt();
    		
    		int result = power(N, M);
    				
    		System.out.println("#" + T + " " + result);
		}
    }

	public static int power(int n, int m) {
		if (m == 0) {
			return 1;
		}
		return n * power(n, m-1);
	}
}
    	
