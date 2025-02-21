import java.io.*;
import java.util.*;

public class Solution {
	static int[][] matrix;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static Queue<int[]> q;
	
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 1; t <= 10; t++) {
        	int T = Integer.parseInt(br.readLine());
        	
        	q = new LinkedList<>();
        	
        	matrix = new int[16][16];
        	for (int i = 0; i < 16; i++) {
				String line[] = br.readLine().split("");
				for (int j = 0; j < 16; j++) {
					matrix[i][j] = Integer.parseInt(line[j]);
					
					if (matrix[i][j] == 2) {
						q.add(new int[] {i, j});
					}
				}
			}
        	System.out.println("#" + t + " " + bfs());
        }
	}

	public static int bfs() {
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				
				if (nx>=0 && nx<16 && ny>=0 && ny<16) {
					if (matrix[nx][ny] == 3) {
						return 1;
					}
					if (matrix[nx][ny] == 0) {
						q.add(new int[] {nx, ny});
						matrix[nx][ny] = 1;
					}
				}
			}
		}
		return 0;
	}
}
