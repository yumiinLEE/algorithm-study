import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[7];

    // 동, 서, 북, 남
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        x = Integer.parseInt(st.nextToken()); // 초기 x 위치
        y = Integer.parseInt(st.nextToken()); // 초기 y 위치
        K = Integer.parseInt(st.nextToken()); // 명령 수

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;

            // 이동할 위치 계산
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }

            // 주사위 회전
            rollDice(dir);

            // 위치 갱신
            x = nx;
            y = ny;

            if (map[x][y] == 0) {
                // 지도가 0이면 주사위 바닥면 값을 복사
                map[x][y] = dice[6];
            } else {
                // 지도가 0이 아니면, 지도 값을 주사위 바닥면에 복사하고 지도는 0으로
                dice[6] = map[x][y];
                map[x][y] = 0;
            }

            // 윗면 값 출력
            sb.append(dice[1]).append("\n");
        }

        System.out.print(sb);
    }

    /**
     * 주어진 방향 따라 주사위 면을 회전시키는 함수
     */
    static void rollDice(int dir) {
        int top = dice[1];
        int north = dice[2];
        int east = dice[3];
        int west = dice[4];
        int south = dice[5];
        int bottom = dice[6];

        // 방향에 따라 주사위 면을 재배치
        if (dir == 0) { // 동쪽
            dice[1] = west;   // 왼쪽이 윗면으로
            dice[3] = top;    // 윗면이 오른쪽으로
            dice[4] = bottom; // 바닥이 왼쪽으로
            dice[6] = east;   // 오른쪽이 바닥으로
        } else if (dir == 1) { // 서쪽
            dice[1] = east;
            dice[3] = bottom;
            dice[4] = top;
            dice[6] = west;
        } else if (dir == 2) { // 북쪽
            dice[1] = south;
            dice[2] = top;
            dice[5] = bottom;
            dice[6] = north;
        } else if (dir == 3) { // 남쪽
            dice[1] = north;
            dice[2] = bottom;
            dice[5] = top;
            dice[6] = south;
        }
    }
}
