import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 개수
            int E = Integer.parseInt(st.nextToken()); // 간선 개수
            
            List<Integer>[] graph = new ArrayList[V + 1];
            int[] inDegree = new int[V + 1];
            
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                inDegree[to]++;
            }
            
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);    // 진입 차수가 0인 노드 큐에 추가
                }
            }
            
            sb.append("#").append(t).append(" ");
            
            while (!q.isEmpty()) {
                int current = q.poll();
                sb.append(current).append(" ");
                
                for (int next : graph[current]) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
}