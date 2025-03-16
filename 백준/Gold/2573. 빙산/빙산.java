import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] matrix;
	static boolean visited[][];
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int cnt;  // 빙산 덩어리 개수
	static int year;  // 년도 수
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	matrix = new int[N][M];
    	
    	for (int i = 0; i < N; i++) {
    		String[] line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(line[j]);
			}
		}
    	
    	// 빙산이 녹는 과정
    	while(true) {
    		int[][] melting = new int[N][M];  // 각 빙산이 녹을 양을 저장할 배열
    		
    		// 1. 빙산이 녹을 양을 먼저 계산
	    	for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if (matrix[i][j] > 0) {
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							if (matrix[nx][ny] == 0) {
                                melting[i][j]++;
							}
						}
					}
				}
			}
	    	
	    	// 2. 녹을 양을 한 번에 반영
            boolean all = true;  // 모든 빙산이 녹았는지 체크
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (matrix[i][j] > 0) {
                        matrix[i][j] = Math.max(0, matrix[i][j] - melting[i][j]);
                        if (matrix[i][j] > 0) {
                        	all = false;  // 아직 남아 있는 빙산이 있음
                        }
                    }
                }
            }
            
            // 년도 증가
	    	year++;
            
            // 3. 모든 빙산이 녹았다면 0 출력 후 종료
            if (all) {
                System.out.println(0);
                return;
            }
	    	
            // 4. 빙산 덩어리 개수 계산
	    	cnt = 0;
	    	visited = new boolean[N][M];
	    	
	    	for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if (matrix[i][j] > 0 && !visited[i][j]) {  // 방문하지 않은 빙산이 있으면 BFS 실행
						bfs(i, j);
						cnt++;
					}
				}
	    	}
	    	
	    	// 2개 이상의 덩어리로 분리되면 종료
	    	if (cnt >= 2) {
				System.out.println(year);
				return;
			}
		}
    }

    
    // BFS를 사용하여 빙산 덩어리 탐색
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int cx = current[0];
			int cy = current[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				// 범위 내에 있고, 방문하지 않은 빙산이면 큐에 추가
				if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && matrix[nx][ny] > 0) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
}