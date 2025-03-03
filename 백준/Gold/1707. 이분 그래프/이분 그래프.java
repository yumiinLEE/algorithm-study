import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph; // 그래프를 저장할 리스트 배열
    static int[] colors; // 각 정점의 색상을 저장 (0: 미방문, 1: 빨강, -1: 파랑)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertices = Integer.parseInt(st.nextToken()); // 정점 개수
            int edges = Integer.parseInt(st.nextToken()); // 간선 개수
            
            graph = new ArrayList[vertices + 1];
            colors = new int[vertices + 1];
            
            for (int i = 1; i <= vertices; i++) {
                graph[i] = new ArrayList<>();
            }
            
            // 간선 정보를 입력받아 그래프 구성
            for (int i = 0; i < edges; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
            
            // 그래프가 이분 그래프인지 확인 후 결과 저장
            if (isBipartite(vertices)) {
                result.append("YES\n");
            } else {
                result.append("NO\n");
            }
        }
        
        System.out.print(result);
    }
    
    // 그래프가 이분 그래프인지 검사하는 함수
    static boolean isBipartite(int vertices) {
        for (int i = 1; i <= vertices; i++) {
            if (colors[i] == 0) { // 아직 방문하지 않은 정점이면 BFS 수행
                if (!bfs(i)) {
                    return false; // 하나라도 이분 그래프가 아니면 false 반환
                }
            }
        }
        return true; // 모든 정점이 이분 그래프 조건을 만족하면 true 반환
    }
    
    // BFS를 이용한 이분 그래프 판별 함수
    static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1; // 시작 정점을 빨강(1)으로 색칠
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph[node]) { // 현재 정점과 연결된 모든 정점 확인
                if (colors[neighbor] == 0) { // 방문하지 않은 정점이면
                    colors[neighbor] = -colors[node]; // 현재 정점과 반대 색상으로 칠하기
                    queue.offer(neighbor); // 다음 탐색을 위해 큐에 추가
                } else if (colors[neighbor] == colors[node]) { // 인접한 두 정점이 같은 색이면
                    return false; // 이분 그래프가 아님
                }
            }
        }
        return true; // 모든 정점을 올바르게 색칠하면 이분 그래프
    }
}