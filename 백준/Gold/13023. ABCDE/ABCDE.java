import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] friends;
    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 사람 수
        M = Integer.parseInt(st.nextToken());  // 친구 관계 수
        
        // 친구 관계를 저장할 배열 초기화
        friends = new ArrayList[N];
        for (int i = 0; i < N; i++) {
        	friends[i] = new ArrayList<>();
        }
        
        // 친구 관계 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            friends[u].add(v);
            friends[v].add(u);
		}
        
        // 모든 사람에 대해 DFS를 수행해봄
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, 0);
            if (found) {
                break;
            }
        }
        
        // 결과 출력
        if (found) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    
    
    // DFS 함수 (현재 사람, 깊이를 매개변수로)
    public static void dfs(int current, int depth) {
        if (depth == 4) {  // A-B-C-D-E까지 이어졌다면
            found = true;
            return;
        }

        visited[current] = true;  // 현재 사람을 방문 처리
        
        // 현재 사람과 친구인 사람들을 순차적으로 탐색
        for (int next : friends[current]) {
            if (!visited[next]) {  // 방문하지 않은 친구라면
                dfs(next, depth + 1);
                if (found) return;  // 찾았다면 더 이상 진행하지 않음
            }
        }
        
        visited[current] = false;  // 탐색이 끝났다면 방문 처리 해제
    }
}