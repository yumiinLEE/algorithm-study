import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] innings; // 이닝별 각 타자의 결과
    static boolean[] selected = new boolean[9]; // 타순 선택 여부
    static int[] order = new int[9]; // 타순
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                innings[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 4번 타자에 1번 선수 고정
        selected[0] = true;
        order[3] = 0;
        
        makeOrder(0);

        System.out.println(maxScore);
    }

    
    static void makeOrder(int depth) {
        if (depth == 9) {
            maxScore = Math.max(maxScore, simulate());
            return;
        }

        if (depth == 3) {
            makeOrder(depth + 1); // 4번 타자는 이미 고정이므로 건너뛴다
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                order[depth] = i;
                makeOrder(depth + 1);
                selected[i] = false;
            }
        }
    }

    // 경기
    static int simulate() {
        int score = 0;
        int player = 0;

        for (int inning = 0; inning < N; inning++) {
            int out = 0;
            boolean[] base = new boolean[3]; // 1루, 2루, 3루

            while (out < 3) {
                int batter = order[player]; // 현재 타자 번호
                int result = innings[inning][batter];

                if (result == 0) {
                    out++;
                } else {
                    // 주자 이동
                    score += moveRunners(base, result);
                }

                player = (player + 1) % 9;
            }
        }

        return score;
    }

    // 주자 이동 및 점수 계산
    static int moveRunners(boolean[] base, int hit) {
        int score = 0;

        if (hit == 1) { // 안타
            if (base[2]) score++;
            if (base[1]) base[2] = true; else base[2] = false;
            if (base[0]) base[1] = true; else base[1] = false;
            base[0] = true;
        }
        else if (hit == 2) { // 2루타
            if (base[2]) score++;
            if (base[1]) score++;
            if (base[0]) base[2] = true; else base[2] = false;
            base[1] = true;
            base[0] = false;
        }
        else if (hit == 3) { // 3루타
            for (int i = 0; i < 3; i++) {
                if (base[i]) score++;
                base[i] = false;
            }
            base[2] = true;
        }
        else if (hit == 4) { // 홈런
            for (int i = 0; i < 3; i++) {
                if (base[i]) score++;
                base[i] = false;
            }
            score++; // 타자도 홈까지
        }

        return score;
    }
}