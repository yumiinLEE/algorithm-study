import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] box = new int[100];
			for (int i = 0; i < 100; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}
						
			for (int i = 0; i < N; i++) {
				Arrays.sort(box);
				box[99]--;
				box[0]++;
			}
            
            Arrays.sort(box);
            
			System.out.println("#"+t+" "+ (box[99]-box[0]));
		}
	}
}
