import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dist;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Point implements Comparable<Point> {
        int x, y, cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }

    public static int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        dist = new int[N][N];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        pq.offer(new Point(0, 0, map[0][0]));
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            if (cur.cost > dist[cur.x][cur.y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    int nextCost = cur.cost + map[nx][ny];
                    if (nextCost < dist[nx][ny]) {
                        dist[nx][ny] = nextCost;
                        pq.offer(new Point(nx, ny, nextCost));
                    }
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map = new int[N][N];
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < N; y++) {
                    map[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = bfs();
            System.out.println("Problem " + T + ": " + answer);
            T++;
        }
    }
}