import java.io.*;
import java.util.*;

public class Solution {
	static String[] num = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			
			int[] cnt = new int[10];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				String word = st.nextToken();
				for (int j = 0; j < 10; j++) {
					if (word.equals(num[j])) {
						cnt[j]++;
						break;
					}
				}
			}
            
			System.out.print("#" + t + "\n");
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < cnt[i]; j++) {
					System.out.print(num[i] + " ");
				}
			}
		}
	}
}