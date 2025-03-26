import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 현재 시간 A
            int B = Integer.parseInt(st.nextToken()); // 추가할 시간 B
            
            // 새로운 시간 계산
            int result = (A + B) % 24;
            System.out.println("#" + t + " " + result);
        }
    }
}