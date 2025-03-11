import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int vertex;
		int weight;
		
		Edge(int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
        
		// 우선순위 큐에서 가중치가 작은 순으로 나오도록 비교
	    public int compareTo(Edge other) {
	        return Integer.compare(this.weight, other.weight);
	    }
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 정점의 개수 V, 간선의 개수 E
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// 시작 정점
		int start = Integer.parseInt(br.readLine());
		
		// 인접 리스트 초기화
		List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 정보 입력 (v -> e, 가중치 w)
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(v).add(new Edge(e, w));
		}
		
		// 최단 거리 배열
		int dist[] = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// 시작노드의 거리는 0
		dist[start] = 0;
		
		// 우선순위 큐 (가중치가 작은 순서대로 정점을 꺼내기 위해 사용)
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
			int cu = current.vertex;
			int cDist = current.weight;
			
			// 이미 더 작은 거리가 계산된 경우는 처리할 필요 없음
            if (cDist > dist[cu]) {
                continue;
            }
			
            // 현재 정점에서 인접한 모든 정점에 대해 거리 계산
			for (Edge edge : graph.get(cu)) {
				int nv = edge.vertex;
				int nw = edge.weight;
				int nDist = cDist + nw;  // 시작점에서 해당 정점까지의 거리
				 
				
				// 더 작은 거리가 발견되면 갱신하고 우선순위 큐에 추가
				if (nDist < dist[nv]) {
					dist[nv] = nDist;
					pq.add(new Edge(nv, nDist));
				}
			}
		}
		
		
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");  // 도달할 수 없는 경우 'INF' 출력
            } else {
                sb.append(dist[i]).append("\n");  // 최단 거리 출력
            }
        }
        System.out.print(sb.toString());
	}
}
