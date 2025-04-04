import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int maxSum = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 위치에서 DFS 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
                checkTShape(i, j);
            }
        }

        System.out.println(maxSum);
    }

    // DFS를 활용한 탐색 (4칸을 선택하는 방식)
    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + board[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    // 'ㅗ', 'ㅜ', 'ㅓ', 'ㅏ' 모양을 체크하는 함수
    static void checkTShape(int x, int y) {
        int sum = 0;
        // ㅗ
        if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < M) {
            sum = board[x][y] + board[x - 1][y] + board[x][y - 1] + board[x][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
        // ㅜ
        if (x + 1 < N && y - 1 >= 0 && y + 1 < M) {
            sum = board[x][y] + board[x + 1][y] + board[x][y - 1] + board[x][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
        // ㅓ
        if (x - 1 >= 0 && x + 1 < N && y - 1 >= 0) {
            sum = board[x][y] + board[x - 1][y] + board[x + 1][y] + board[x][y - 1];
            maxSum = Math.max(maxSum, sum);
        }
        // ㅏ
        if (x - 1 >= 0 && x + 1 < N && y + 1 < M) {
            sum = board[x][y] + board[x - 1][y] + board[x + 1][y] + board[x][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
    }
}
