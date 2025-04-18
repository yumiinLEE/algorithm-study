import java.io.*;
import java.util.*;

public class Main {
    static class Wire implements Comparable<Wire> {
        int a, b;
        public Wire(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        @Override
        public int compareTo(Wire other) {
            return this.a - other.a; // A 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Wire[] wires = new Wire[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(a, b);
        }

        // A 기준 정렬
        Arrays.sort(wires);

        // B값들만 꺼내서 LIS(최장 증가 부분 수열) 구하기
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (wires[j].b < wires[i].b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int lis = 0;
        for (int i = 0; i < n; i++) {
            lis = Math.max(lis, dp[i]);
        }

        System.out.println(n - lis);
    }
}