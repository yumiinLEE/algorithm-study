import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int K = Integer.parseInt(st.nextToken()); // 목표 거리
        int X = Integer.parseInt(st.nextToken()); // 출발 도시

        // 그래프 초기화
        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }
        
        bfs(graph, N, X, K);
    }

    private static void bfs(List<Integer>[] graph, int N, int start, int K) {
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[N + 1]; // 각 도시까지의 최단 거리 저장
        Arrays.fill(distance, -1); // 방문하지 않은 도시는 -1로 초기화

        queue.add(start);
        distance[start] = 0; // 시작 도시는 거리 0

        List<Integer> result = new ArrayList<>(); // K 거리의 도시 저장 리스트

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 인접한 도시 탐색
            for (int next : graph[current]) {
                if (distance[next] == -1) { // 방문하지 않은 도시만 큐에 추가
                    distance[next] = distance[current] + 1;
                    queue.add(next);

                    // 목표 거리 K에 도달한 경우 리스트에 추가
                    if (distance[next] == K) {
                        result.add(next);
                    }
                }
            }
        }

        // 결과 리스트가 비어 있으면 -1 출력, 아니면 정렬 후 출력
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result); // 오름차순 정렬
            for (int city : result) {
                System.out.println(city);
            }
        }
    }
}