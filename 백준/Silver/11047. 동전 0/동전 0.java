import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 동전 종류 개수
        int K = Integer.parseInt(st.nextToken()); // 목표 금액
        int[] coins = new int[N];
        
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        
        int count = 0;
        
        // 큰 동전부터 사용하기 위해 배열을 뒤에서부터 탐색
        for (int i = N - 1; i >= 0; i--) {
            if (K >= coins[i]) {
                count += K / coins[i]; // 해당 동전으로 만들 수 있는 개수 추가
                K %= coins[i]; // 남은 금액 갱신
            }
        }
        
        System.out.println(count); // 최소 동전 개수 출력
    }
}
