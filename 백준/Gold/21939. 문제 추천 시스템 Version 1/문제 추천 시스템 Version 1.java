import java.io.*;
import java.util.*;

public class Main {

    // 문제번호와 레벨을 저장
    static HashMap<Integer, Integer> problems = new HashMap<>();
    
    // 난이도가 높은 문제를 우선으로 꺼내는 큐
    static PriorityQueue<Problem> maxHeap = new PriorityQueue<>((a, b) -> {
        if (a.level == b.level) {
        	return b.num - a.num; // 난이도가 같으면 번호가 큰 것이 우선
        }
        return b.level - a.level; // 난이도가 높은 것이 우선
    });
    
    // 난이도가 낮은 문제를 우선으로 꺼내는 큐
    static PriorityQueue<Problem> minHeap = new PriorityQueue<>((a, b) -> {
        if (a.level == b.level) {
        	return a.num - b.num; // 난이도가 같으면 번호가 작은 것이 우선
        }
        return a.level - b.level; // 난이도가 낮은 것이 우선
    });


    static class Problem {
        int num;
        int level;
        
        Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 문제 리스트 크기 N
        int N = Integer.parseInt(br.readLine());

        // 문제 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            problems.put(P, L);
            maxHeap.offer(new Problem(P, L));
            minHeap.offer(new Problem(P, L));
        }
        
        // 명령어 개수 M
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            
            if (command.equals("recommend")) {
            	int x = Integer.parseInt(st.nextToken());
                
                if (x == 1) {
                    // 가장 어려운 문제 출력
                    while (!maxHeap.isEmpty()) {
                        Problem top = maxHeap.peek();
                        // problems에 존재하지 않는 문제이거나 난이도가 다르면 제거
                        if (!problems.containsKey(top.num) || problems.get(top.num) != top.level) {
                            maxHeap.poll();
                        } else {
                            System.out.println(top.num);
                            break;
                        }
                    }
                } else if (x == -1) {
                    // 가장 쉬운 문제 출력
                    while (!minHeap.isEmpty()) {
                        Problem top = minHeap.peek();
                        // problems에 존재하지 않는 문제이거나 난이도가 다르면 제거
                        if (!problems.containsKey(top.num) || problems.get(top.num) != top.level) {
                            minHeap.poll();
                        } else {
                            System.out.println(top.num);
                            break;
                        }
                    }
                }
            } 
            else if (command.equals("add")) {
            	int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                problems.put(P, L);
                maxHeap.offer(new Problem(P, L));
                minHeap.offer(new Problem(P, L));  
            }
            else if (command.equals("solved")) {
            	int P = Integer.parseInt(st.nextToken());
                problems.remove(P);
            }
        }
    }
}
