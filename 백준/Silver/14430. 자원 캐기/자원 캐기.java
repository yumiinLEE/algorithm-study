import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] matrix = new int[N+1][M+1];
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M+1; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][M+1];
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
			}
		}
		System.out.println(dp[N][M]);
	}
}