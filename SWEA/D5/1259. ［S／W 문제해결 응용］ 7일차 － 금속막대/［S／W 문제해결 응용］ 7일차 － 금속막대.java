import java.io.*;
import java.util.*;

public class Solution {
    static class Rod {
        int male;
        int female;

        Rod(int male, int female) {
            this.male = male;
            this.female = female;
        }
    }

    static List<Rod> rods;        // 원래의 막대 리스트
    static boolean[] used;        // 사용 여부
    static List<Rod> answer;      // 최종 결과
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());  // 막대 개수
            rods = new ArrayList<>();
            used = new boolean[N];
            answer = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int male = Integer.parseInt(st.nextToken());
                int female = Integer.parseInt(st.nextToken());
                rods.add(new Rod(male, female));
            }

            // 모든 막대를 시작점으로 시도
            for (int i = 0; i < N; i++) {
                used[i] = true;
                List<Rod> temp = new ArrayList<>();
                temp.add(rods.get(i));
                dfs(temp);
                used[i] = false;
            }

            // 출력
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");
            for (Rod rod : answer) {
                sb.append(rod.male).append(" ").append(rod.female).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    // DFS로 체인 만들기
    static void dfs(List<Rod> current) {
        if (current.size() == N) {
            answer = new ArrayList<>(current);  // 가장 긴 체인 저장
            return;
        }

        int lastFemale = current.get(current.size() - 1).female;

        for (int i = 0; i < N; i++) {
            if (!used[i] && rods.get(i).male == lastFemale) {
                used[i] = true;
                current.add(rods.get(i));
                dfs(current);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
}
