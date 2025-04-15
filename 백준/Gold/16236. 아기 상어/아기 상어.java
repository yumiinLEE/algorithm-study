import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1}; // 상, 좌, 우, 하 순서 (우선순위 고려)
    static int[] dy = {0, -1, 1, 0};

    static class Shark implements Comparable<Shark> {
        int x, y, dist;

        Shark(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.dist == o.dist) {
                if (this.x == o.x)
                    return this.y - o.y;
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int sx = 0, sy = 0; // 아기 상어 시작 위치

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0; // 상어 위치는 빈 칸으로 초기화
                }
            }
        }

        int time = 0;
        int size = 2;
        int eat = 0;

        while (true) {
            visited = new boolean[N][N];
            PriorityQueue<Shark> pq = new PriorityQueue<>();
            Queue<Shark> queue = new LinkedList<>();

            queue.offer(new Shark(sx, sy, 0));
            visited[sx][sy] = true;

            boolean found = false;

            while (!queue.isEmpty()) {
                Shark s = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = s.x + dx[i];
                    int ny = s.y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                        if (map[nx][ny] <= size) { // 지나갈 수 있는 칸
                            visited[nx][ny] = true;
                            queue.offer(new Shark(nx, ny, s.dist + 1));

                            if (map[nx][ny] != 0 && map[nx][ny] < size) {
                                pq.offer(new Shark(nx, ny, s.dist + 1));
                                found = true;
                            }
                        }
                    }
                }
            }

            if (!found) break; // 먹을 수 있는 물고기가 없음

            Shark target = pq.poll(); // 가장 우선순위 높은 물고기
            sx = target.x;
            sy = target.y;
            time += target.dist;

            map[sx][sy] = 0; // 물고기 먹기
            eat++;

            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }
}
