import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine()); // 수식 길이
            String expression = br.readLine();           // 중위 표기식
            
            // 1. 중위 표기식을 후위 표기식으로 변환
            StringBuilder postfix = new StringBuilder();
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < N; i++) {
                char c = expression.charAt(i);

                if (Character.isDigit(c)) { // 숫자는 바로 후위 표기식에 추가
                    postfix.append(c);
                } 
                else if (c == '(') { // 여는 괄호는 무조건 스택에 저장
                    stack.push(c);
                } 
                else if (c == ')') { // 닫는 괄호가 나오면 '(' 전까지 연산자 꺼내기
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop());
                    }
                    stack.pop(); // '(' 제거
                } 
                else { // 연산자 (+, *)
                    while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
                        postfix.append(stack.pop());
                    }
                    stack.push(c);
                }
            }

            // 스택에 남은 연산자 처리
            while (!stack.isEmpty()) {
                postfix.append(stack.pop());
            }

            // 2. 후위 표기식을 계산
            Stack<Integer> calcStack = new Stack<>();

            for (int i = 0; i < postfix.length(); i++) {
                char c = postfix.charAt(i);

                if (Character.isDigit(c)) { // 숫자는 스택에 저장
                    calcStack.push(c - '0'); // 문자 -> 숫자 변환
                } 
                else { // 연산자라면 두 숫자를 꺼내서 계산 후 다시 저장
                    int num2 = calcStack.pop();
                    int num1 = calcStack.pop();
                    if (c == '+') {
                        calcStack.push(num1 + num2);
                    } 
                    else if (c == '*') {
                        calcStack.push(num1 * num2);
                    }
                }
            }

            int result = calcStack.pop(); // 최종 결과

            // 결과 출력
            System.out.println("#" + t + " " + result);
        }
    }

    // 연산자 우선순위 반환 함수
    private static int getPriority(char op) {
        if (op == '*') return 2; // 곱셈(*)이 더 우선순위 높음
        if (op == '+') return 1;
        return 0;
    }
}