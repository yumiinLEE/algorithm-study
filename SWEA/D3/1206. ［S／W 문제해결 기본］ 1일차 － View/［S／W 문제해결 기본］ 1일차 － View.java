import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] building = new int[N+4];
			for (int i = 0; i < N; i++) {
				building[i] = Integer.parseInt(st.nextToken());
			}
			
			int cnt = 0;
			
			for (int i = 2; i < N+2; i++) {
				int left1 = building[i-1];
				int left2 = building[i-2];
				int right1 = building[i+1];
				int right2 = building[i+2];
				
				int leftmax = Math.max(left1, left2);
				int rightmax = Math.max(right1, right2);
				
				int max = Math.max(leftmax, rightmax);
				
				if (building[i] > max) {
					cnt += building[i] - max;
				}
			}
			System.out.println("#"+t+" "+ cnt);
		}
	}
}
