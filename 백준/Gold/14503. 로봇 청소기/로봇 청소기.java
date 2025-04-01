import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] matrix;
    static boolean[][] visited;
    static int r, c, d;
    static int[] dx = {-1, 0, 1, 0}; // 0:북, 1:동, 2:남, 3:서
    static int[] dy = {0, 1, 0, -1};
    
    static class Robot {
        int x, y, dir;
        
        Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs(r, c, d));
    }

    static int bfs(int x, int y, int dir) {
        int cnt = 0;
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, dir));

        while (!q.isEmpty()) {
            Robot current = q.poll();
            int cx = current.x;
            int cy = current.y;
            int cd = current.dir;
            
            boolean moved = false;

            // 현재 위치 청소
            if (matrix[cx][cy] == 0 && !visited[cx][cy]) {
                visited[cx][cy] = true;
                cnt++;
            }

            // 반시계방향으로 회전하면서 청소되지 않은 빈 칸을 찾는다
            for (int i = 0; i < 4; i++) {
                // 반시계방향 회전
                cd = (cd + 3) % 4;
                int nx = cx + dx[cd];
                int ny = cy + dy[cd];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && matrix[nx][ny] == 0 && !visited[nx][ny]) {
                    q.add(new Robot(nx, ny, cd));  // 이동
                    moved = true;
                    break;
                }
            }

            // 4방향 모두 청소된 경우 또는 벽으로 막힌 경우
            if (!moved) {
                // 후진 가능 여부 체크
                int backDir = (cd + 2) % 4;  // 현재 방향에서 180도 반대 방향
                int bx = cx + dx[backDir];
                int by = cy + dy[backDir];

                // 후진할 수 있다면 후진
                if (0 <= bx && bx < N && 0 <= by && by < M && matrix[bx][by] == 0) {
                    q.add(new Robot(bx, by, cd));
                } else {
                    break;  // 후진할 수 없으면 종료
                }
            }
        }
        
        return cnt;  // 청소한 칸의 개수
    }
}