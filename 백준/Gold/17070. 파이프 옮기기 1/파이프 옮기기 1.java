import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] matrix;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
            	matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        // DFS를 이용해 경로의 수 계산
        int result = dfs(0, 1, 0);

        System.out.println(result);
    }

    
    static int dfs(int r, int c, int dir) {
    	
        if (r == N - 1 && c == N - 1) {
            return 1;
        }

        int result = 0;

        // 가로 상태
        if (dir == 0) {
            // 오른쪽으로 이동
            if (c + 1 < N && matrix[r][c + 1] == 0) {
                result += dfs(r, c + 1, 0);
            }
            // 대각선 방향으로 이동
            if (r + 1 < N && c + 1 < N && matrix[r + 1][c + 1] == 0 && matrix[r][c + 1] == 0 && matrix[r + 1][c] == 0) {
                result += dfs(r + 1, c + 1, 2);
            }
            
        // 세로 상태
        } else if (dir == 1) {
            // 아래로 이동
            if (r + 1 < N && matrix[r + 1][c] == 0) {
                result += dfs(r + 1, c, 1);
            }
            // 대각선 방향으로 이동
            if (r + 1 < N && c + 1 < N && matrix[r + 1][c + 1] == 0 && matrix[r][c + 1] == 0 && matrix[r + 1][c] == 0) {
                result += dfs(r + 1, c + 1, 2);
            }
            
        // 대각선 상태
        } else if (dir == 2) {
            // 오른쪽으로 이동
            if (c + 1 < N && matrix[r][c + 1] == 0) {
                result += dfs(r, c + 1, 0);
            }
            // 아래로 이동
            if (r + 1 < N && matrix[r + 1][c] == 0) {
                result += dfs(r + 1, c, 1);
            }
            // 대각선으로 이동
            if (r + 1 < N && c + 1 < N && matrix[r + 1][c + 1] == 0 && matrix[r][c + 1] == 0 && matrix[r + 1][c] == 0) {
                result += dfs(r + 1, c + 1, 2);
            }
        }

        return result;
    }
}
