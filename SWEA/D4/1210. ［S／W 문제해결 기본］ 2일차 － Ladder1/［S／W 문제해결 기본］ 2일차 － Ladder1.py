import java.io.*;
import java.util.*;

public class Solution {
    static int[][] matrix;
    static int x, y;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine());
            
            matrix = new int[100][100];
            
            // 사다리 입력받기
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());

                    if (matrix[i][j] == 2) {  // 2이면 도착점
                        x = j;  // x좌표 저장
                        y = i;  // y좌표 저장
                    }
                }
            }

            // 위로 올라가면서 경로 추적
            while (y > 0) {
                // 왼쪽에 1이 있으면 왼쪽으로 이동
                if (x > 0 && matrix[y][x - 1] == 1) {
                    // 왼쪽으로 계속 이동
                    while (x > 0 && matrix[y][x - 1] == 1) {
                        x--;
                    }
                }
                // 오른쪽에 1이 있으면 오른쪽으로 이동
                else if (x < 99 && matrix[y][x + 1] == 1) {
                    // 오른쪽으로 계속 이동
                    while (x < 99 && matrix[y][x + 1] == 1) {
                        x++;
                    }
                }
                // 위로 이동
                y--;
            }

            // 출발점의 x 좌표 출력
            System.out.println("#" + t + " " + x);
        }
    }
}
