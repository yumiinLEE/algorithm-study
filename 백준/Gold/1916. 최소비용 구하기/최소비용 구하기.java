import java.io.*;
import java.util.*;

public class Main {
	
	static class Bus implements Comparable<Bus> {
		int end;
		int cost;
		
		Bus(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
		
		// 우선순위 큐에서 비용이 작은 순으로 나오도록 비교
		public int compareTo(Bus other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 도시의 개수 N과 버스의 개수 M
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		// 도시별로 갈 수 있는 버스 정보를 저장할 인접 리스트
		List<List<Bus>> city = new ArrayList<>();
		
		// 도시 초기화
		for (int i = 0; i <= N; i++) {
			city.add(new ArrayList<>());
		}
		
		// 버스 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 출발 도시 a에서 도착 도시 b로 가는 버스의 비용 c 추가
			city.get(a).add(new Bus(b, c));
		}
		
		// 출발 도시와 도착 도시
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 비용 배열
		int[] cost = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[start] = 0; // 출발 도시는 0으로 설정
		
		// 우선순위 큐 사용 (비용, 도시) 순으로 큐에 넣기
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.add(new Bus(start, 0));
		
		// 다익스트라 알고리즘 수행
		while (!pq.isEmpty()) {
			Bus current = pq.poll();
			int cend = current.end;
			int ccost = current.cost;
			
            // 이미 더 작은 비용으로 방문한 적이 있다면 스킵
            if (ccost > cost[cend]) {
                continue;
            }
            
			// 현재 도시에서 연결된 모든 도시를 확인
			for (Bus bus : city.get(cend)) {
				int nend = bus.end;
				int ncost = ccost + bus.cost;
				
				// 더 작은 비용으로 갈 수 있으면 갱신
				if (ncost < cost[nend]) {
					cost[nend] = ncost;
					pq.add(new Bus(nend, ncost));
				}
			}
		}
		
		System.out.println(cost[end]);
	}
}
