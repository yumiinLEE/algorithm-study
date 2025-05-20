import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] matrix; // 0: 공기, 1: 치즈
    static boolean[][] visited; // 방문 체크
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int time = 0;
    static int lastCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][M];

            markAir(0, 0); // 외부 공기 마킹

            List<int[]> meltList = new ArrayList<>();
            int currentCheese = 0;

            // 치즈 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] == 1) {
                        currentCheese++;
                        if (isTouchingAir(i, j)) {
                            meltList.add(new int[]{i, j});
                        }
                    }
                }
            }

            if (currentCheese == 0) {
                System.out.println(time);
                System.out.println(lastCnt);
                break;
            }

            lastCnt = currentCheese;

            // 한꺼번에 녹이기
            for (int[] melt : meltList) {
                matrix[melt[0]][melt[1]] = 0;
            }

            time++;
        }
    }

    // 외부 공기 마킹
    private static void markAir(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (matrix[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 외부 공기와 접촉한 치즈인지 확인
    private static boolean isTouchingAir(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (matrix[nx][ny] == 0 && visited[nx][ny]) {
                    return true;
                }
            }
        }
        return false;
    }
}
