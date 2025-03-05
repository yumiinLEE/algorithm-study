import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            // 수강생 수 N, 제출한 사람 수 K 입력
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[1]);
            
            // 제출한 사람 번호 입력
            String[] submitted = br.readLine().split(" ");
            
            // visited 배열: false = 제출하지 않음, true = 제출함
            boolean[] visited = new boolean[N + 1]; // 1부터 N까지 인덱스를 사용
            
            // 제출한 사람의 번호를 visited 배열에 true로 표시
            for (String student : submitted) {
                visited[Integer.parseInt(student)] = true;
            }
            
            System.out.print("#" + t);
            // 과제를 제출하지 않은 사람 출력 (오름차순)
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    System.out.print(" " + i);
                }
            }
            System.out.println();
        }
    }
}
