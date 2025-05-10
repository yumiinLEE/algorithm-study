import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int pos;
        int time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
            
    static final int MAX = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(N, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.pos] < now.time) continue;

            // 순간이동 (0초)
            if (now.pos * 2 < MAX && dist[now.pos * 2] > now.time) {
                dist[now.pos * 2] = now.time;
                pq.offer(new Node(now.pos * 2, now.time));
            }

            // 걷기 -1
            if (now.pos - 1 >= 0 && dist[now.pos - 1] > now.time + 1) {
                dist[now.pos - 1] = now.time + 1;
                pq.offer(new Node(now.pos - 1, now.time + 1));
            }

            // 걷기 +1
            if (now.pos + 1 < MAX && dist[now.pos + 1] > now.time + 1) {
                dist[now.pos + 1] = now.time + 1;
                pq.offer(new Node(now.pos + 1, now.time + 1));
            }
        }

        System.out.println(dist[K]);
    }
}



// 순간이동은 시간이 0초이므로 우선순위가 높은 작업이며, 이를 처리하기 위해 Deque를 사용

// import java.io.*;
// import java.util.*;

// public class Main {
//     static final int MAX = 100_001;

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         int N = Integer.parseInt(st.nextToken());
//         int K = Integer.parseInt(st.nextToken());

//         int[] dist = new int[MAX];
//         Arrays.fill(dist, -1);

//         Deque<Integer> deque = new ArrayDeque<>();
//         dist[N] = 0;
//         deque.add(N);

//         while (!deque.isEmpty()) {
//             int now = deque.poll();

//             if (now == K) {
//                 System.out.println(dist[now]);
//                 return;
//             }

//             if (now * 2 < MAX && dist[now * 2] == -1) {
//                 dist[now * 2] = dist[now];
//                 deque.addFirst(now * 2);
//             }

//             if (now - 1 >= 0 && dist[now - 1] == -1) {
//                 dist[now - 1] = dist[now] + 1;
//                 deque.addLast(now - 1);
//             }

//             if (now + 1 < MAX && dist[now + 1] == -1) {
//                 dist[now + 1] = dist[now] + 1;
//                 deque.addLast(now + 1);
//             }
//         }
//     }
// }
