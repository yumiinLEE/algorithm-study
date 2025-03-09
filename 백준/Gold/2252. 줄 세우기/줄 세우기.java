import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 비교 횟수

        List<Integer>[] graph = new ArrayList[N + 1];
        int[] order = new int[N + 1]; // 키 순서 배열

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            order[B]++; // B의 키 순서 증가
        }

        // 위상 정렬 (Topological Sort)
        Queue<Integer> queue = new PriorityQueue<>(); // 작은 숫자부터 출력해야 하므로 PriorityQueue 사용
        for (int i = 1; i <= N; i++) {
            if (order[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int student = queue.poll();
            sb.append(student).append(" ");

            for (int next : graph[student]) {
            	order[next]--;
                if (order[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}