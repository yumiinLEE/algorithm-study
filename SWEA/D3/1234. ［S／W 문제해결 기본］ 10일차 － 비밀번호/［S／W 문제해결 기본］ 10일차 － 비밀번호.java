import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String line = st.nextToken();
            
            // Deque 사용 (스택처럼 처리)
            Deque<Character> stack = new ArrayDeque<>();

            for (char num : line.toCharArray()) {
                // 스택이 비어 있지 않고, 현재 값과 스택의 마지막 값이 같으면 pop
                if (!stack.isEmpty() && stack.peekLast() == num) {
                    stack.pollLast();  // 가장 마지막 값 제거 (pop)
                } else {
                    stack.offerLast(num);  // 새로운 값은 스택에 추가 (push)
                }
            }

            // 결과 출력: 스택에 남은 문자들 출력
            StringBuilder result = new StringBuilder();
            for (char c : stack) {
                result.append(c);
            }

            System.out.println("#" + t + " " + result.toString());
        }
    }
}
