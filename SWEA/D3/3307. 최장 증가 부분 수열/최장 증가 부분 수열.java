import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] dp = new int[N];
            Arrays.fill(dp, 1); // 최소 길이는 1 (자기 자신만으로도 LIS 길이가 됨)
            int maxLIS = 1;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxLIS = Math.max(maxLIS, dp[i]);
            }
            
            System.out.println("#" + t + " " + maxLIS);
        }
    }
}