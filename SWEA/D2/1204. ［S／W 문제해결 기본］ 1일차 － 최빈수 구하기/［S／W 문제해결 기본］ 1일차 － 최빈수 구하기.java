import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] score = new int[101];
			for (int i = 0; i < 1000; i++) {
				score[Integer.parseInt(st.nextToken())]++;
			}
			
			int num = 0;
			int index = 0;
			for (int i = 0; i <= 100; i++) {
				if (score[i]>=num) {
					num = score[i];
					index = i;
				}
			}
			System.out.println("#"+t+" "+ index);
		}
	}
}
