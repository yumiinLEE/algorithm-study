import java.io.*;
import java.util.*;

public class Main {
    static class Task implements Comparable<Task> {
        int deadline, score;

        Task(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }

        @Override
        public int compareTo(Task o) {
            return o.deadline - this.deadline;  // 마감일 기준 내림차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Task> tasks = new ArrayList<>();
        int maxDeadline = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tasks.add(new Task(d, w));
            maxDeadline = Math.max(maxDeadline, d);
        }

        Collections.sort(tasks);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        int idx = 0;

        // 가장 늦은 마감일부터 1일까지 하루씩 거꾸로 순회
        for (int day = maxDeadline; day >= 1; day--) {

            // 현재 날짜(day)에 마감일이 같은 과제들 중 아직 큐에 넣지 않은 과제들을 모두 우선순위 큐에 추가
            // => 오늘 혹은 이후에도 가능한 과제들을 큐에 넣는다
            while (idx < N && tasks.get(idx).deadline >= day) {
                pq.offer(tasks.get(idx).score);  // 점수를 우선순위 큐에 삽입 (자동으로 높은 점수가 먼저 나오도록 설정됨)
                idx++;  // 다음 과제로 인덱스 이동
            }

            // 오늘 할 수 있는 과제 중 가장 점수가 높은 과제를 하나 선택해서 수행
            if (!pq.isEmpty()) {
                answer += pq.poll();  // 우선순위 큐에서 가장 큰 점수 꺼내서 결과에 누적
            }
        }

        System.out.println(answer);
    }
}
