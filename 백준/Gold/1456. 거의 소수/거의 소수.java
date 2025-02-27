import java.io.*;
import java.util.*;
 
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        int limit = (int) Math.sqrt(B);
        boolean[] prime = new boolean[limit+1];
        
        Arrays.fill(prime, true);
        
        prime[0] = false;
        prime[1] = false;
        
        
        // 에라토스테네스의 체로 소수 판별
        for (int i = 2; i*i <= limit; i++) {
			if (prime[i]) {
				for (int j = i*i; j <= limit; j+=i) {
					prime[j] = false;
				}
			}
		}
        
        List<Long> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
			if (prime[i]) {
				primes.add((long) i);
			}
		}
        
        int count = 0;

        // 거의 소수 찾기
        for (long p : primes) {
            long num = p * p; // p^2부터 시작
            while (num <= B) {
                if (num >= A) count++;
                if (num > B / p) break; // 오버플로우 방지 (==> num*p > B)
                num *= p;
            }
        }

        System.out.println(count);
    }
}
    