import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] matrix;
	static Set<Character> set;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int max;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        matrix = new char[R][C];
        for (int i = 0; i < R; i++) {
        	String line = br.readLine();
			for (int j = 0; j < C; j++) {
				matrix[i][j] = line.charAt(j);
			}
		}
        
        // 방문한 알파벳을 저장할 Set
		set = new HashSet<>();
        dfs(0, 0, 1, set);
        
        System.out.println(max);
	}

	static void dfs(int x, int y, int cnt, Set<Character> set) {
		max = Math.max(max, cnt);

		set.add(matrix[x][y]);
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0 <= nx && nx < R && 0 <= ny && ny < C && !set.contains(matrix[nx][ny])) {
				set.add(matrix[nx][ny]);
				dfs(nx, ny, cnt+1, set);
				set.remove(matrix[nx][ny]);
			}
		}
	}
}