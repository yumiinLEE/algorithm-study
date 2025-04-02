import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            String S = br.readLine();
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int length = S.length();
            int totalShift = 0;

            // 연산 횟수 K번
            for (int k = 0; k < K; k++) {
                int X = Integer.parseInt(st.nextToken());

                // X 자체를 문자열 길이로 나눈 나머지만 누적
                totalShift = (totalShift + X) % length;
            }

            // 음수일 때 양수로 변환
            if (totalShift < 0) {
                totalShift += length;
            }

            // 문자열 이동
            S = S.substring(totalShift) + S.substring(0, totalShift);

            sb.append(S).append("\n");
        }

        System.out.print(sb);
    }
}