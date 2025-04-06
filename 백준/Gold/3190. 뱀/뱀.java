import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] board;
    static Deque<int[]> snake = new LinkedList<>();  // 양쪽 끝에서 삽입과 삭제가 모두 가능한 자료구조
    static Map<Integer, Character> dirChange = new HashMap<>();

    // 방향: 0=오른쪽, 1=아래, 2=왼쪽, 3=위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y] = 1; // 사과
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            dirChange.put(sec, c);
        }

        System.out.println(playGame());
    }

    static int playGame() {
        int time = 0;
        int dir = 0;
        int x = 0, y = 0;
        snake.add(new int[]{x, y});
        board[x][y] = 2; // 뱀 몸 표시

        while (true) {
            time++;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 벽 충돌 or 자기 자신 충돌
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2) {
                break;
            }

            // 사과 있으면 -> 사과 제거, 꼬리 그대로
            if (board[nx][ny] == 1) {
                board[nx][ny] = 2;
                snake.addFirst(new int[]{nx, ny});
            }
            // 사과 없으면 -> 꼬리 이동
            else {
                board[nx][ny] = 2;
                snake.addFirst(new int[]{nx, ny});
                int[] tail = snake.removeLast();
                board[tail[0]][tail[1]] = 0;
            }

            // 방향 전환 체크
            if (dirChange.containsKey(time)) {
                char c = dirChange.get(time);
                if (c == 'L') {
                    dir = (dir + 3) % 4;
                } else if (c == 'D') {
                    dir = (dir + 1) % 4;
                }
            }

            x = nx;
            y = ny;
        }

        return time;
    }
}
