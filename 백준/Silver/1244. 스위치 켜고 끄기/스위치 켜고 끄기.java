import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 스위치 개수
        
        int[] switches = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine()); // 학생 수
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken()); // 성별 (1: 남학생, 2: 여학생)
            int num = Integer.parseInt(st.nextToken()); // 스위치 번호
            
            if (gender == 1) { // 남학생 (배수 위치 반전)
                for (int j = num; j <= N; j += num) {
                    switches[j] = 1 - switches[j]; // 스위치 반전
                }
            } else { // 여학생 (좌우 대칭 반전)
                int left = num, right = num;
                
                // 좌우 대칭 범위 찾기
                while (left > 0 && right <= N && switches[left] == switches[right]) {
                    left--;
                    right++;
                }
                
                // while 문에서 한 번 더 이동했으므로 원래 범위로 조정
                left++;
                right--;

                // 범위 내 스위치 반전
                for (int j = left; j <= right; j++) {
                    switches[j] = 1 - switches[j];
                }
            }
        }

        // 20개 출력 후 줄 바꿈
        for (int j = 1; j <= N; j++) {
            System.out.print(switches[j] + " ");
            if (j % 20 == 0) {
                System.out.println();
            }
        }
    }
}