import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[] company = new int[2];
    static int[] home = new int[2];
    static int[][] customers;
    static boolean[] visited;
    static int minDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            customers = new int[N][2];
            visited = new boolean[N];
            minDistance = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 회사 좌표
            company[0] = Integer.parseInt(st.nextToken());
            company[1] = Integer.parseInt(st.nextToken());

            // 집 좌표
            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());

            // 고객 좌표
            for (int i = 0; i < N; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }

            // 순열을 통해 모든 경우 탐색
            perm(0, company[0], company[1], 0);

            System.out.println("#" + tc + " " + minDistance);
        }
    }

    // 순열을 생성하면서 거리 계산
    static void perm(int cnt, int x, int y, int dist) {
        if (dist >= minDistance) return; // 가지치기

        if (cnt == N) {
            // 모든 고객을 다 방문했으면 집까지 거리 추가
            dist += Math.abs(x - home[0]) + Math.abs(y - home[1]);
            minDistance = Math.min(minDistance, dist);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nx = customers[i][0];
                int ny = customers[i][1];
                int d = Math.abs(x - nx) + Math.abs(y - ny);
                perm(cnt + 1, nx, ny, dist + d);
                visited[i] = false;
            }
        }
    }
}
