import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to;
        int weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N, E;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 수, 간선 수 입력
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향 그래프이므로 양쪽 모두에 간선 추가
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        // 반드시 거쳐야 하는 두 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1번, v1, v2에서 각각 출발하는 다익스트라 실행
        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        // 두 가지 경로 중 더 짧은 것을 선택
        long path1 = (long)distFrom1[v1] + distFromV1[v2] + distFromV2[N]; // 1 → v1 → v2 → N
        long path2 = (long)distFrom1[v2] + distFromV2[v1] + distFromV1[N]; // 1 → v2 → v1 → N

        long result = Math.min(path1, path2);

        if (result >= Integer.MAX_VALUE) {
            System.out.println(-1); // 도달 불가능한 경우
        } else {
            System.out.println(result); // 최단 경로 출력
        }
    }

    // 다익스트라 알고리즘
    static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1]; // 시작 노드로부터의 거리 배열
        Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열 초기화
        dist[start] = 0; // 시작점까지 거리는 0
        pq.offer(new Edge(start, 0)); // 시작점 큐에 삽입

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int now = current.to;

            // 이미 더 짧은 거리로 방문했다면 무시
            if (dist[now] < current.weight) continue;

            // 현재 노드에서 인접한 노드들을 확인
            for (Edge next : graph[now]) {
                int cost = dist[now] + next.weight;

                // 더 짧은 경로 발견 시 갱신
                if (cost < dist[next.to]) {
                    dist[next.to] = cost;
                    pq.offer(new Edge(next.to, cost));
                }
            }
        }

        return dist;
    }
}
