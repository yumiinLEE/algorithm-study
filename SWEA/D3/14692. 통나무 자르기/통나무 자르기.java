import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            int N = Integer.parseInt(br.readLine());

            if (N % 2 == 0) {
                bw.write("#" + t + " Alice\n");
            } else {
                bw.write("#" + t + " Bob\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}