import java.io.*;

public class Main {
    static int M, N;
    static int[][] map, dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        M = Integer.parseInt(firstLine[0]);
        N = Integer.parseInt(firstLine[1]);
        
        map = new int[M][N];
        dp = new int[M][N];
        
        // 지도 입력 받기
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // DP 배열 -1로 초기화
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        // (0, 0)에서 (M-1, N-1)까지의 경로 개수 계산
        System.out.println(dfs(0, 0));
    }

    
    // (x, y)에서 출발하여 목표 지점까지 가는 경로의 개수를 DFS로 계산
    public static int dfs(int x, int y) {
        // 목표 지점에 도달했으면 1을 반환
        if (x == M - 1 && y == N - 1) {
            return 1;
        }
        
        // 이미 계산한 값이 있으면 그 값을 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 경로의 개수를 저장할 변수
        int count = 0;

        // 상하좌우 네 방향을 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 지도 범위 내에 있고, 내리막길이면 이동
            if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] < map[x][y]) {
                count += dfs(nx, ny);  // 이동 가능한 경로 수를 더함
            }
        }

        // 계산한 경로 수를 dp[x][y]에 저장
        dp[x][y] = count;

        return dp[x][y];
    }
}
