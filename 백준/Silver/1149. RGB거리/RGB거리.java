import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());  // 집의 수 N
        int[][] cost = new int[N][3];  // 각 집을 칠하는 비용 (0: 빨강, 1: 초록, 2: 파랑)
        int[][] dp = new int[N][3];  // DP 테이블
        
        // 비용 입력 받기
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(input[0]);  // 빨간색 비용
            cost[i][1] = Integer.parseInt(input[1]);  // 초록색 비용
            cost[i][2] = Integer.parseInt(input[2]);  // 파란색 비용
        }
        
        // 첫 번째 집은 색상에 상관없이 칠할 수 있음
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        
        // DP로 각 집을 칠하는 최소 비용 구하기
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];  // 빨간색으로 칠할 때
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];  // 초록색으로 칠할 때
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];  // 파란색으로 칠할 때
        }
        
        // 마지막 집을 칠할 때, 3가지 색 중 최소 비용을 구함
        int result = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]);
        
        System.out.println(result);
    }
}