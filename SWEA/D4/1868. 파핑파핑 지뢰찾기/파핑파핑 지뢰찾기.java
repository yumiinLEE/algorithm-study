import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static String[][] matrix;
	static boolean[][] visited;  // 각 칸의 방문 여부를 확인
	static int[] dx = {-1, 0, -1, 0, 1, -1, 1, 1};
	static int[] dy = {-1, -1, 1, 1, 1, 0, -1, 0};
	
	static class Position {
		int x;
		int y;
		
		Position (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			matrix = new String[N][N];
			
			// 배열 입력받기
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					matrix[i][j] = line[j];
				}
			}
			
			// 주변 지뢰 개수 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j].equals(".")) {
						matrix[i][j] = Integer.toString(surround(i, j));
					}
				}
			}
			
			visited = new boolean[N][N];
			
			int ans = 0;
			// 연속된 영역을 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 주위 지뢰가 없고 아직 방문하지 않았다면, BFS를 이용하여 탐색
					if (matrix[i][j].equals("0") && !visited[i][j]) {
						bfs(i, j);
						// 하나의 영역을 찾았다면 정답 증가
						ans++;
					}
				}
			}
			
			// 남아있는 지뢰가 아닌 칸들 카운트
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!matrix[i][j].equals("*") && !visited[i][j]) {
						ans++;
					}
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	// 8방향 지뢰 카운트 함수
	static int surround(int x, int y) {
		int cnt = 0;
		
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				// "*"이라면 지뢰이므로 카운트
				if (matrix[nx][ny].equals("*")) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 연속된 영역을 탐색
	static void bfs(int x, int y) {
		Queue<Position>  q = new LinkedList<>();
		
		q.add(new Position(x, y));
		visited[x][y] = true;
        
		while (!q.isEmpty()) {
			Position current = q.poll();
			int cx = current.x;
			int cy = current.y;
			
			for (int i = 0; i < 8; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					// 현재 칸 주위에 지뢰가 없으면 8방향 칸들 BFS 큐에 추가
					if (matrix[cx][cy].equals("0") && !visited[nx][ny]) {
						q.add(new Position(nx, ny));
                        visited[nx][ny] = true;
					}
				}
			}
		}
	}
}