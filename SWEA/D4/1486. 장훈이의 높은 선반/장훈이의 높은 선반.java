import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int B;
	static int[] arr;
	static int ans;
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            
            arr = new int[N];
        	st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
            
            ans = Integer.MAX_VALUE;
            dfs(0, 0);
            
            System.out.println("#" + t + " " + (ans-B));
        }
    }

	public static void dfs(int n, int sum) {
		if (sum >= B) {
			ans = Math.min(ans, sum);
			return;
		}
		
		if (n >= N) {
			return;
		}
		
		dfs(n+1, sum+arr[n]);
		dfs(n+1, sum);
	}
}