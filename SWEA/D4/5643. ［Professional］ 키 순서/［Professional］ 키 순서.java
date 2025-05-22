import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());  

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());  // 학생 수
            int M = Integer.parseInt(br.readLine());  // 비교 횟수

            // 학생 간 키 비교 결과를 저장할 그래프
            boolean[][] graph = new boolean[N + 1][N + 1];

            // M개의 비교 정보 입력받기
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());  // a번 학생
                int b = Integer.parseInt(st.nextToken());  // b번 학생
                graph[a][b] = true;  // a < b 이므로 a -> b
            }

            // 플로이드-워셜 알고리즘으로 간접적인 키 비교 관계 파악
            for (int k = 1; k <= N; k++) {          // 경유 노드
                for (int i = 1; i <= N; i++) {       // 출발 노드
                    for (int j = 1; j <= N; j++) {   // 도착 노드
                        // i < k 이고 k < j 이면 i < j 임을 유추할 수 있음
                        if (graph[i][k] && graph[k][j]) {
                            graph[i][j] = true;
                        }
                    }
                }
            }

            int answer = 0;  // 자신의 정확한 순위를 알 수 있는 학생 수

            // 모든 학생에 대해 검사
            for (int i = 1; i <= N; i++) {
                int smaller = 0;  // 자신보다 키가 작은 학생 수
                int taller = 0;   // 자신보다 키가 큰 학생 수

                for (int j = 1; j <= N; j++) {
                    if (graph[i][j]) taller++;      // i < j 이면 i보다 큰 학생
                    if (graph[j][i]) smaller++;     // j < i 이면 i보다 작은 학생
                }

                // 자신보다 큰 학생 수 + 작은 학생 수 = N-1 이면 정확한 순위 파악 가능
                if (taller + smaller == N - 1) {
                    answer++;
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }
}
