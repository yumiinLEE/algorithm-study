import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            // N: 상자 수, Q: 작업 수
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int Q = Integer.parseInt(line[1]);
            
            // 상자 배열
            int[] boxes = new int[N];
            
            // Q개의 작업 처리
            for (int i = 1; i <= Q; i++) {
                String[] range = br.readLine().split(" ");
                int L = Integer.parseInt(range[0]) - 1;
                int R = Integer.parseInt(range[1]) - 1;
                
                // L부터 R까지의 값을 i로 변경
                for (int j = L; j <= R; j++) {
                    boxes[j] = i;
                }
            }
            
            System.out.print("#" + t + " ");
            for (int i = 0; i < N; i++) {
                System.out.print(boxes[i] + " ");
            }
            System.out.println();
        }
    }
}