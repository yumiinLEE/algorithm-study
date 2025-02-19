import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int num = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N은 숫자 카드 묶음의 개수
        N = Integer.parseInt(br.readLine());
        
        // 우선순위 큐를 사용하여 최소값을 빠르게 꺼내고 합치는 방식으로 진행
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 입력받은 카드 묶음 크기를 우선순위 큐에 넣음
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            pq.add(input);
        }

        // 우선순위 큐에서 두 묶음을 꺼내서 합치고 다시 큐에 넣는 작업 반복
        // 큐에 하나의 묶음만 남을 때까지 진행
        while (pq.size() > 1) {
            // 두 개의 가장 작은 묶음을 꺼냄 (우선순위 큐에서 가장 작은 값부터 꺼내짐)
            int first = pq.poll();
            int second = pq.poll();
            
            // 두 묶음을 합친 값 (비교 횟수 계산)
            num += first + second;

            // 합친 묶음을 다시 큐에 넣음
            pq.add(first + second);
        }
        
        // 결과 출력 (최소 비교 횟수)
        System.out.println(num);
    }
}
