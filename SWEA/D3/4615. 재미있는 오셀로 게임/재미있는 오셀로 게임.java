import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int[][] board;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 8방향 탐색
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) { // 테스트 케이스
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 보드 크기
            M = Integer.parseInt(st.nextToken()); // 돌을 놓는 횟수
            
            board = new int[N][N];
            // 초기 돌 배치
            int mid = N / 2;
            board[mid - 1][mid - 1] = 2;
            board[mid][mid] = 2;
            board[mid - 1][mid] = 1;
            board[mid][mid - 1] = 1;
            
            // 돌 놓기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());
                
                placeStone(x, y, color);
            }
            // 결과 계산
            int black = 0, white = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1) black++;
                    else if (board[i][j] == 2) white++;
                }
            }
            
            System.out.println("#" + t + " " + black + " " + white);
        }
        br.close();
    }
    

    // 돌 놓기 함수
    private static void placeStone(int x, int y, int color) {
        board[x][y] = color;
        
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            List<int[]> toFlip = new ArrayList<>();
            
            while (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] != 0 && board[nx][ny] != color) {
                toFlip.add(new int[]{nx, ny});
                nx += dx[d];
                ny += dy[d];
            }
            
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == color) {
                for (int[] pos : toFlip) {
                    board[pos[0]][pos[1]] = color;
                }
            }
        }
    }
}

