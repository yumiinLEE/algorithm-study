import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 물건 수
            int K = Integer.parseInt(st.nextToken()); // 가방 최대 부피

            int[] V = new int[N + 1]; // 부피
            int[] C = new int[N + 1]; // 가치

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {
                for (int w = 0; w <= K; w++) {
                    if (w < V[i]) {
                        dp[i][w] = dp[i - 1][w]; // 담을 수 없으면 이전 값 유지
                    } else {
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - V[i]] + C[i]); // 담거나 말거나
                    }
                }
            }

            System.out.println("#" + t + " " + dp[N][K]);
        }
    }
}