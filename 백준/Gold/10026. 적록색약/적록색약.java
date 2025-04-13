import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new char[N][N];

        for (int i = 0; i < N; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        // 일반인 시각
        visited = new boolean[N][N];
        int normalCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, matrix[i][j], false);
                    normalCnt++;
                }
            }
        }

        // 적록색약 시각
        visited = new boolean[N][N];
        int colorWeakCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, matrix[i][j], true);
                    colorWeakCnt++;
                }
            }
        }

        System.out.println(normalCnt + " " + colorWeakCnt);
    }
    
    
    public static void dfs(int x, int y, char color, boolean isColorWeak) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny]) {
                    if (isColorWeak) {
                        if ((isRedGreen(color) && isRedGreen(matrix[nx][ny])) || color == matrix[nx][ny]) {
                            dfs(nx, ny, color, true);
                        }
                    } else {
                        if (matrix[nx][ny] == color) {
                            dfs(nx, ny, color, false);
                        }
                    }
                }
            }
        }
    }

    // R 또는 G인지 확인
    public static boolean isRedGreen(char c) {
        return c == 'R' || c == 'G';
    }

}
