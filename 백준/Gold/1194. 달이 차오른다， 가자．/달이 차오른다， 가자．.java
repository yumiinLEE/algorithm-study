import java.util.*;

public class Main {
    static int N, M;
    static char[][] maze;
    static int[] dx = {-1, 1, 0, 0};  // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    
    static boolean[][][] visited;  // visited[y][x][key state] = true면 해당 상태를 방문했다는 의미
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();  // 개행 문자 처리
        
        maze = new char[N][M];
        int startX = 0, startY = 0;
        
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == '0') {  // 민식이의 시작 위치
                    startX = j;
                    startY = i;
                }
            }
        }

        // visited 배열: visited[y][x][keyState] = true면 해당 상태를 방문했다는 의미
        visited = new boolean[N][M][64];  // 최대 2^6 = 64개의 열쇠 상태
        
        // BFS 큐 (x, y, keyState, distance)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 0, 0});
        visited[startY][startX][0] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0], x = current[1], keyState = current[2], dist = current[3];
            
            // 출구에 도달하면
            if (maze[y][x] == '1') {
                System.out.println(dist);
                return;
            }
            
            // 상, 하, 좌, 우 탐색
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && maze[ny][nx] != '#') {
                    char cell = maze[ny][nx];
                    
                    // 문을 만나면 열쇠가 있는지 확인
                    if (cell >= 'A' && cell <= 'F') {
                        int door = cell - 'A';  // 문에 해당하는 열쇠를 가지고 있는지
                        if ((keyState & (1 << door)) == 0) {
                            continue;  // 열쇠가 없으면 지나갈 수 없음
                        }
                    }
                    
                    // 열쇠를 찾았을 경우, keyState 갱신
                    int newKeyState = keyState;
                    if (cell >= 'a' && cell <= 'f') {
                        int key = cell - 'a';
                        newKeyState |= (1 << key);  // 해당 열쇠를 주웠다면 keyState를 갱신
                    }
                    
                    // 방문하지 않은 곳이라면 큐에 추가
                    if (!visited[ny][nx][newKeyState]) {
                        visited[ny][nx][newKeyState] = true;
                        queue.add(new int[]{ny, nx, newKeyState, dist + 1});
                    }
                }
            }
        }

        // 미로를 탈출할 수 없으면 -1 출력
        System.out.println(-1);
    }
}
