import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] matrix;
	static int startX, startY;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        
        matrix = new char[N][M];
        
        for (int i = 0; i < N; i++) {
        	matrix[i] = br.readLine().toCharArray();
        	for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 'I') {
					startX = i;
					startY = j;
				}
			}
		}
        
        int result = bfs(startX, startY);
        
        if (result == 0) {
            System.out.println("TT");
		}else {
			System.out.println(result);
		}
    }

	private static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
					if (matrix[nx][ny] == 'P') {
						cnt++;
					}
					if (matrix[nx][ny] == 'P' || matrix[nx][ny] == 'O') {
						q.add(new int[] {nx, ny});
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		return cnt;
	}
}