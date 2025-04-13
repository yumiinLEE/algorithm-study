import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Treasure {
        int x, y, dist;

        Treasure(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        int maxDistance = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    maxDistance = Math.max(maxDistance, bfs(i, j));
                }
            }
        }

        System.out.println(maxDistance);
    }

    static int bfs(int x, int y) {
        visited = new boolean[N][M];
        Queue<Treasure> queue = new LinkedList<>();
        queue.add(new Treasure(x, y, 0));
        visited[x][y] = true;

        int max = 0;

        while (!queue.isEmpty()) {
            Treasure cur = queue.poll();
            max = Math.max(max, cur.dist);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    queue.add(new Treasure(nx, ny, cur.dist + 1));
                }
            }
        }
        return max;
    }
}