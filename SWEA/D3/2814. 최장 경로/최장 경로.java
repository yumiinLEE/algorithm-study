import java.io.*;
import java.util.*;

public class Solution {
    static int N;                     // 정점의 개수
    static int max;                   // 최장 경로 길이 저장
    static List<Integer>[] graph;      // 그래프의 인접 리스트
    static boolean[] visited;          // 방문 여부 체크 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
        
        for (int t = 1; t <= T; t++) {  
            max = 0;  // max 값 초기화
            
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 정점 개수
            int M = Integer.parseInt(st.nextToken()); // 간선 개수
            
            graph = new LinkedList[N + 1]; // 정점 번호는 1번부터 N번까지 사용
            
            for (int i = 1; i <= N; i++) {
                graph[i] = new LinkedList<>(); // 인접 리스트 초기화
            }
            
            for (int i = 0; i < M; i++) { // 간선 입력
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                
                graph[u].add(v);
                graph[v].add(u);
            }
            
            for (int i = 1; i <= N; i++) { // 모든 정점에서 DFS 탐색 시작
                visited = new boolean[N + 1]; // 방문 배열 초기화
                visited[i] = true;  // 현재 정점 방문 처리
                dfs(1, i);  // DFS 실행 (현재 길이 1부터 시작)
            }
            
            System.out.println("#" + t + " " + max); // 결과 출력
        }
    }

    public static void dfs(int length, int node) {
        max = Math.max(max, length);  // 최장 경로 갱신

        for (Integer next : graph[node]) { // 현재 노드와 연결된 모든 노드 탐색
            if (!visited[next]) { // 방문하지 않은 노드라면
                visited[next] = true;  // 방문 처리
                dfs(length + 1, next); // DFS 재귀 호출
                visited[next] = false; // 백트래킹: 방문 해제 (다른 경로 탐색을 위해)
            }
        }
    }
}
