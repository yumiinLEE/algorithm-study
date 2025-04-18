import java.io.*;
import java.util.*;

public class Main {
    static int[][] paper = new int[10][10];
    static int[] papers = {0, 5, 5, 5, 5, 5}; // 색종이 크기별 남은 개수
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void dfs(int x, int y, int count) {
        if (y == 10) {
            dfs(x + 1, 0, count);
            return;
        }

        if (x == 10) {
            if (answer > count) {
                answer = count;
            }
            return;
        }

        if (paper[x][y] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (papers[size] > 0 && canAttach(x, y, size)) {
                    attach(x, y, size, 0); // 색종이 붙이기
                    papers[size]--;
                    dfs(x, y + 1, count + 1);
                    attach(x, y, size, 1); // 색종이 떼기
                    papers[size]++;
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }

    static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) {
            return false;
        }
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (paper[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void attach(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                paper[i][j] = value;
            }
        }
    }
}
