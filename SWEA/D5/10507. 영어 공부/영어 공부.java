import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 공부한 날 수
            int p = Integer.parseInt(st.nextToken()); // 가짜로 채울 수 있는 날 수

            int[] days = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                days[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            int start = 0;
            int end = 0;

          while (end < n) {
                int studied = end - start + 1;  // 실제 공부한 날 수
                int len = days[end] - days[start] + 1; // 현재 윈도우 크기
                int gap = len - studied;  // 가짜로 채워야 하는 날 수 = (윈도우 크기) - (공부한 날 수)

                if (gap <= p) {
					// 전체 연속 길이는 실제 공부 + 채울 수 있는 날
                    max = Math.max(max, studied + p);
                    end++;
                } else {
                    start++;
                }
            }

            System.out.println("#" + t + " " + max);
        }
    }
}