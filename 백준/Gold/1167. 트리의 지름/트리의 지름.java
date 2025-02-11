import java.io.*;
import java.util.*;

public class Main {
    static int V;  // 정점 개수
    static List<int[]>[] tree; // 인접 리스트 (정점, 거리)
    static boolean[] visited;
    static int maxDist = 0, farthestNode = 0; // 최대 거리, 가장 먼 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        tree = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }

        // 트리 정보 입력
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());

            while (true) {
                int adj = Integer.parseInt(st.nextToken());
                if (adj == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                tree[node].add(new int[]{adj, dist});
            }
        }

        // 1. 임의의 정점 (1번 노드)에서 가장 먼 노드 찾기
        visited = new boolean[V + 1];
        bfs(1);

        // 2. 찾은 노드에서 다시 가장 먼 노드 찾기 (트리의 지름)
        visited = new boolean[V + 1];
        maxDist = 0;
        bfs(farthestNode);

        // 정답 출력
        System.out.println(maxDist);
    }

    // BFS를 이용하여 가장 먼 노드 찾기
    public static void bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], dist = cur[1];

            if (dist > maxDist) { // 최대 거리 갱신
                maxDist = dist;
                farthestNode = node;
            }

            for (int[] next : tree[node]) {
                int nextNode = next[0], nextDist = next[1];
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(new int[]{nextNode, dist + nextDist});
                }
            }
        }
    }
}
