import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] hackCount;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[B].add(A);  // B가 A를 신뢰
        }
        
        hackCount = new int[N + 1];  // 각 노드에서 해킹할 수 있는 개수 저장
        int maxHack = 0;
        
        // 모든 노드에서 BFS 실행
        for (int i = 1; i <= N; i++) {
            int count = bfs(i);
            hackCount[i] = count;
            maxHack = Math.max(maxHack, count);
        }
        
        // 가장 많은 해킹이 가능한 노드 찾기
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (hackCount[i] == maxHack) {
                sb.append(i).append(" ");
            }
        }
        
        System.out.println(sb.toString().trim());
    }
    
    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        
        q.add(start);
        visited[start] = true;
        
        int count = 1;  // 자기 자신 포함
        while (!q.isEmpty()) {
            int current = q.poll();
            
            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }
        
        return count;
    }
}
