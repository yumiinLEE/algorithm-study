import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, K;  // N: 도시의 수, M: 도로의 수, K: 구하고자 하는 K번째 최단경로
	static List<Path>[] city;  // 도시 간 연결된 도로 리스트
	static List<Integer>[] kTime;  // 각 도시의 경로 시간 리스트
	
	static class Path implements Comparable<Path>{
		int end;
		int time;
		
		Path(int end, int time) {
			this.end = end;
			this.time = time;
		}

		@Override
		public int compareTo(Path o) {
			return Integer.compare(this.time, o.time);  // 시간을 기준으로 경로 비교
		}	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 각 도시별로 연결된 도로 리스트와 경로 시간을 저장할 리스트 초기화
		city = new ArrayList[N+1];
		kTime = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) {
			city[i] = new ArrayList<>();
			kTime[i] = new ArrayList<>();
		}
		
		// 도로 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());  // 출발 도시
            int b = Integer.parseInt(st.nextToken());  // 도착 도시
            int c = Integer.parseInt(st.nextToken());  // 걸리는 시간
			
			city[a].add(new Path(b, c));
		}

		// 다익스트라 기반의 BFS 함수 호출
		bfs(1);
		
		// 각 도시별로 k번째 최단 경로 시간 출력
		for (int i = 1; i < N+1; i++) {
			if (kTime[i].size() < K) {
				System.out.println(-1);
			}
			else {  
				System.out.println(kTime[i].get(K-1));
			}
		}
	}

	static void bfs(int start) {
		// 경로를 시간 순으로 처리하는 우선순위 큐
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		pq.add(new Path(start, 0));  // 시작 지점에서 출발
		kTime[start].add(0);
		
		while (!pq.isEmpty()) {
			Path current = pq.poll();
			int curend = current.end;
			int curtime = current.time;
			
			// 현재 도시에서 갈 수 있는 모든 경로 탐색
			for (Path next : city[curend]) {
				int nextTime = curtime + next.time;
				
				// kTime[next.end]의 크기가 K개 미만이면 새로운 경로를 추가
				if (kTime[next.end].size() < K) {
					kTime[next.end].add(nextTime);
					pq.add(new Path(next.end, nextTime));
					Collections.sort(kTime[next.end]);  // 경로들을 시간 순으로 정렬
				}
				// 새로운 경로 시간이 K번째 최단 경로보다 작으면 갱신
				else if (nextTime < kTime[next.end].get(K-1)) {
					kTime[next.end].add(nextTime);
					pq.add(new Path(next.end, nextTime));
					Collections.sort(kTime[next.end]);
				}
			}
		}
	}
}