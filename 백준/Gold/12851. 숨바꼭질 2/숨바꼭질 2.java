import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int[] time = new int[MAX]; // 최소 시간
        int[] ways = new int[MAX]; // 경로 수

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        time[N] = 1;
        ways[N] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (next < 0 || next >= MAX) continue;

                if (time[next] == 0) {
                    // 처음 방문: 시간 설정, 방법 수 복사
                    time[next] = time[cur] + 1;
                    ways[next] = ways[cur];
                    queue.offer(next);
                } else if (time[next] == time[cur] + 1) {
                    // 같은 시간에 또 방문 가능하면 경로 수만 누적
                    ways[next] += ways[cur];
                }
            }
        }

        System.out.println(time[K] - 1);
        System.out.println(ways[K]);
    }
}
