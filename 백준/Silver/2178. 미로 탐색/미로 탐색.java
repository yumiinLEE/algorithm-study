import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<int[]> q;
	static int[][] visited;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
        	String[] line = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}
        
        q = new LinkedList<>();
        bfs(0,0);
        
        System.out.println(visited[N-1][M-1]);

    }
    
    static void bfs(int si, int sj) {
    	visited[si][sj] = 1;
    	q.add(new int[] {si, sj});
    	
    	while (!q.isEmpty()) {
    		int[] current = q.poll();
    		int ci = current[0];
    		int cj = current[1];
    		
    		for (int i = 0; i < 4; i++) {
				int ni = ci + dx[i];
				int nj = cj + dy[i];
				
				if (0 <= ni && ni < N && 0 <= nj && nj < M && arr[ni][nj] == 1 && visited[ni][nj] == 0) {
					q.add(new int[] {ni, nj});
					visited[ni][nj] = visited[ci][cj] + 1;
				}
			}
    	}
    }
}