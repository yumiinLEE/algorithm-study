import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int U = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            
            sb.append("#").append(t).append(" ");
            if (X < L) {
                sb.append(L - X);
            } else if (X > U) {
                sb.append(-1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}