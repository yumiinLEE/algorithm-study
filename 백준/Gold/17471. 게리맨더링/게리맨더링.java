import java.io.*;
import java.util.*;

public class Main {
	static int N; // 구역의 개수
	static int[] people; // 각 구역의 인구수
	static List<Integer>[] city; // 각 구역의 인접 구역들
	static int min = Integer.MAX_VALUE; // 인구 차이의 최솟값
	static boolean[] visited; // BFS에서 사용될 방문 여부 배열
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		
		// 구역별 인구 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		// 각 구역의 인접한 구역 정보 입력
		city = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			city[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int connect = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < connect ; j++) {
				int v = Integer.parseInt(st.nextToken());
				city[i].add(v);
			}
		}
		
		// dfs로 두 선거구로 나누기
		dfs(1, new ArrayList<>(), new ArrayList<>());
		
		
		// 최소 인구 차이 출력
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	}
	

	// 구역을 두 선거구로 나누는 dfs 함수
	static void dfs(int n, List<Integer> first, List<Integer> second) {
		if (n == N+1) {
			if (first.size() == 0 || second.size() == 0) {
				return;  // 한 선거구가 비어있으면 return
			}
			
			visited = new boolean[N+1];
			
			// 두 선거구가 각각 연결되어 있는지 확인
			bfs(first.get(0), first);
			bfs(second.get(0), second);
			
			// 모든 구역이 방문되었는지 확인
			for (int i = 1; i < N+1; i++) {
				if (!visited[i]) {
					return;
				}
			}
			
			// 인구 차이 계산 후 최솟값 갱신
			int diff = cal(first, second);
			min = Math.min(min, diff);
			return;
		}
		
		// 구역을 첫 번째 선거구에 넣고 두 번째 선거구에 넣는 두 가지 경우 탐색
		first.add(n);
		dfs(n+1, first, second);
		first.remove(first.indexOf(n));
		
		second.add(n);
		dfs(n+1, first, second);
		second.remove(second.indexOf(n));
	}

	// 주어진 선거구가 연결되어 있는지 확인
	static void bfs(int i, List<Integer> lst) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(i);
		visited[i] = true;
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			for (Integer next : city[current]) {
				if (!visited[next] && lst.contains(next)) {  // 같은 선거구에 있다면(연결되어 있다면)
					visited[next] = true;
					q.add(next);
				}
			}
		}
	}

	// 두 선거구의 인구 차이를 계산
	static int cal(List<Integer> first, List<Integer> second) {
		int fSum = 0;
		int sSum = 0;
		
		// 첫 번째 선거구의 인구 합
		for (int i = 0; i < first.size(); i++) {
			fSum += people[first.get(i)];
		}
		
		// 두 번째 선거구의 인구 합
		for (int i = 0; i < second.size(); i++) {
			sSum += people[second.get(i)];
		}
		
		return Math.abs(fSum - sSum);
	}
}