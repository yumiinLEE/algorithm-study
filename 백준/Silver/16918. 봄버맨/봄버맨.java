import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
    static char[][] matrix;
    static char[][] newMatrix;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 격자판 입력 받기
        matrix = new char[R][C];
        for (int i = 0; i < R; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        // N초가 흐른 후의 격자판을 구하기
        for (int time = 1; time <= N; time++) {
        	
        	if (time == 1) {
				continue;
			}
        	
            // 2초마다 폭탄을 모두 설치
            if (time % 2 == 0) {
            	newMatrix = new char[R][C];
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        newMatrix[i][j] = 'O';
                    }
                }
            }
            
            // 폭탄이 폭발한 상태를 계산
            else {
	            for (int i = 0; i < R; i++) {
	                for (int j = 0; j < C; j++) {
	                    if (matrix[i][j] == 'O') {
	                        // 폭탄이 터지면 그 칸과 인접한 네 칸을 파괴
	                        newMatrix[i][j] = '.';
	                        for (int d = 0; d < 4; d++) {
	                            int nx = i + dx[d];
	                            int ny = j + dy[d];
	                            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
	                                newMatrix[nx][ny] = '.';
	                            }
	                        }
	                    }
	                }
	            }
	            
	            // 격자판을 갱신
	            matrix = newMatrix;
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
        	if (N % 2 == 0) {
        		sb.append(new String(newMatrix[i])).append("\n");
			}
        	else {
        		sb.append(new String(matrix[i])).append("\n");
        	}
        }
        
        System.out.print(sb.toString());
    }
}