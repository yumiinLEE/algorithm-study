import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int[] snack;
	static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            snack = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
            
            max = -1;
            dfs(0, 0, 0);
            
            System.out.println("#" + t + " " + max);
		}
    }

	static void dfs(int count, int start, int sum) {
		if (count == 2) {  // 두 개의 과자를 선택한 경우
			if (sum <= M) {
				max = Math.max(max, sum);
			}
			return;
		}
		
		for (int i = start; i < N; i++) { // i = start부터 시작하여 중복 선택 방지
			dfs(count + 1, i + 1, sum + snack[i]);
		}
	}
}