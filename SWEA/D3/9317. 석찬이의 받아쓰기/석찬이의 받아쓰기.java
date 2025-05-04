import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            String correct = br.readLine();
            String written = br.readLine();

            int count = 0;  // 맞게 적은 문자 수

            for (int i = 0; i < N; i++) {
                if (correct.charAt(i) == written.charAt(i)) {
                    count++;
                }
            }

            System.out.println("#" + t + " " + count);
        }
    }
}