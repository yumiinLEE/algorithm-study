import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr); // 손님 도착 시간 정렬
            
            boolean possible = true;
            for (int i = 0; i <N; i++) {
                int time = arr[i];
                int sum = (time/M)*K;
                
                if (sum < i+1) {
                    possible = false;
                    break;
				}
            }

            if (possible) { // 모든 손님에게 붕어빵을 제공 가능하면
                System.out.println("#" + t + " Possible");
            } else {
                System.out.println("#" + t + " Impossible");
            }
        }
    }
}