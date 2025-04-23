import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static char[][] matrix;
    static int r1, c1, r2, c2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class State implements Comparable<State> {
        int x, y, jump, time;

        State(int x, int y, int jump, int time) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.time = time;
        }

        @Override
        public int compareTo(State o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        matrix = new char[N][N];

        for (int i = 0; i < N; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken()) - 1;
            c1 = Integer.parseInt(st.nextToken()) - 1;
            r2 = Integer.parseInt(st.nextToken()) - 1;
            c2 = Integer.parseInt(st.nextToken()) - 1;

            System.out.println(bfs(r1, c1, r2, c2));
        }
    }

    
    static int bfs(int sx, int sy, int ex, int ey) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        
        // 최소 시간을 저장 visited[x][y][jump]
        int[][][] visited = new int[N][N][6];  // 점프력 1~5
        
        // visited 배열 초기화: 처음에는 모든 위치에 대해 최대값 설정
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        pq.add(new State(sx, sy, 1, 0));
        visited[sx][sy][1] = 0;

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int cx = cur.x;
            int cy = cur.y;
            int cjump = cur.jump;
            int ctime = cur.time;
//            System.out.println((cx+1) + " "+ (cy+1) + " " + cjump + " " + ctime);

            if (cx == ex && cy == ey) {
                return ctime;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i] * cjump;
                int ny = cy + dy[i] * cjump;

                if (0 <= nx && nx < N && 0 <= ny && ny < N && matrix[nx][ny] == '.') {
                    // 점프 경로에 천적이 있는지 확인
                    boolean flag = true;
                    for (int j = 1; j <= cjump; j++) {
                        int midx = cx + dx[i] * j;
                        int midy = cy + dy[i] * j;
                        if (matrix[midx][midy] == '#') {
                            flag = false;
                            break;
                        }
                    }

                    // 장애물이 없고 더 적은 시간이 걸리면 방문 배열 갱신
                    if (flag && visited[nx][ny][cjump] > ctime + 1) {
                        visited[nx][ny][cjump] = ctime + 1;
                        pq.add(new State(nx, ny, cjump, ctime + 1));
                    }
                }
            }

            // 점프력 변경
            for (int i = 1; i <= 5; i++) {
                if (i == cjump) continue;

                int cost = 0;
                if (i < cjump) { // 점프력 i로 줄이기
                    cost = 1;
                } else { // 점프력 i로 늘리기
                    for (int j = cjump + 1; j <= i; j++) {
                        cost += j * j;
                    }
                }

                // 점프력 변경 후 더 빠른 시간이 가능하면 방문 배열 갱신
                if (visited[cx][cy][i] > ctime + cost) {
                    visited[cx][cy][i] = ctime + cost;
                    pq.add(new State(cx, cy, i, ctime + cost));
                }
            }
        }

        return -1;
    }
}

