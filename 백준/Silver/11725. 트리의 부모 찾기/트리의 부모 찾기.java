import java.io.*;
import java.util.*;

public class Main {
	static int[] parent; // 각 노드의 부모를 저장할 배열
	static List<Integer>[] tree; // 트리 구조를 인접 리스트로 표현
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 노드의 개수 N 
		int N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		// 트리의 간선 입력
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 루트 없는 트리이므로 자식에서 부모로 이동할 수 있는 경로가 필요(양방향 연결)
			tree[a].add(b);
			tree[b].add(a);
		}
		
		parent = new int[N+1];
		bfs(1); // 루트 노드 1부터 시작하여 BFS 탐색
		
		for (int i = 2; i < N+1; i++) {
			System.out.println(parent[i]);
		}
	}

	// 부모 노드를 추적
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		parent[start] = 1;  // 루트 노드의 부모는 1번 (자기 자신을 부모로 설정)
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			for (int next : tree[current]) {
				// 아직 부모가 설정되지 않은 노드인 경우
				if (parent[next] == 0) {
					parent[next] = current; // 부모 노드를 current로 설정
					q.add(next);  // 자식 노드를 큐에 추가하여 계속 탐색
				}
			}
		}	
	}
}