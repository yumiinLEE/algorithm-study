import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        
        String[] parts = expression.split("-"); // '-' 기준으로 나누기
        int result = sum(parts[0]); // 첫 번째 부분은 그대로 더함
        
        for (int i = 1; i < parts.length; i++) {
            result -= sum(parts[i]); // 이후 부분은 전부 빼줌
        }
        
        System.out.println(result);
    }
    
    // 주어진 문자열 부분에서 숫자를 더하는 함수
    public static int sum(String s) {
        String[] numbers = s.split("\\+"); // '+' 기준으로 나누기
        int total = 0;
        for (String num : numbers) {
            total += Integer.parseInt(num); // 숫자로 변환하여 더하기
        }
        return total;
    }
}
