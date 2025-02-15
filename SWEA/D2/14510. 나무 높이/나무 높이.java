import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());  // 나무의 수 입력
            
            st = new StringTokenizer(br.readLine());  // 나무들의 높이 입력
            
            int max = 0; // 가장 큰 나무의 높이 저장 변수
            int[] trees = new int[N];  // 나무 높이를 저장할 배열
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());  // 나무 높이 입력
                max = Math.max(max, trees[i]);  // 가장 큰 나무의 높이 찾기
            }
            
            int even = 0, odd = 0;  // 홀수 번째 날은 키가 1 자라고 짝수 번째 날은 키가 2 자람
            for (int i = 0; i < N; i++) {
                int diff = max - trees[i];  // 나무가 자라야 할 높이 차이
                
                if (diff == 0) continue;  // 이미 가장 큰 나무의 높이라면 건너뛰기
                
                even += diff / 2;  // 짝수 번째 날에 물을 준 횟수 누적
                odd += diff % 2;   // 홀수 번째 날에 물을 준 횟수 누적
            }
            
            // 2 -> 1+1로 변경 (even과 odd의 차이를 줄이기 --> 최소 날짜를 구해야하기 때문)
            if (even > odd) {
                while (Math.abs(even - odd) > 1) {  // even과 odd의 차이가 1 이하가 될 때까지 반복
                    even--;
                    odd += 2;
                }
            }
            
            // 결과 계산
            int result = 0;
            if (odd > even) {  // 1이 더 많을 경우
                result = odd * 2 - 1;
            } else if (even > odd) {  // 2가 더 많을 경우
                result = even * 2;
            } else {  // odd와 even이 같을 경우
                result = odd + even;
            }
            
            // 각 테스트 케이스 결과 출력
            System.out.println("#" + t + " " + result);
        }
    }
}
