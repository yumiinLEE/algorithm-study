import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
   
        // N까지의 수에 대한 소수 여부를 기록할 배열
        boolean[] isPrime = new boolean[N + 1];
        
        // 초기값은 모두 소수라고 가정
        Arrays.fill(isPrime, true);
        
        // 0과 1은 소수가 아니므로 false로 설정
        isPrime[0] = isPrime[1] = false;
        
        // 에라토스테네스의 체 알고리즘으로 소수를 구하기
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                // i가 소수라면 i의 배수들을 모두 소수가 아니라고 설정
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        // M부터 N까지 소수를 출력
        for (int i = M; i <= N; i++) {
            if (isPrime[i]) {
                sb.append(i).append("\n");
            }
        }
        
        System.out.print(sb);
    }
}