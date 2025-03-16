import java.io.*;
import java.util.*;

public class Solution {
	static int N, L;
	static int[] score;
	static int[] kal;
	static int max;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	N = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());
        	
        	score = new int[N];
        	kal = new int[N];
        	
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
            	
            	score[i] = Integer.parseInt(st.nextToken());
            	kal[i] = Integer.parseInt(st.nextToken());
			}
        	
        	max = 0;
        	dfs(0, 0, 0);
        	
        	System.out.println("#" + t + " " + max);
		}
    }

	static void dfs(int n, int s, int k) {
		if (n == N) {
			if (k <= L) {
				max = Math.max(max, s);
			}
			return;
		}
		
		dfs(n+1, s+score[n], k+kal[n]);
		dfs(n+1, s, k);
	}
}