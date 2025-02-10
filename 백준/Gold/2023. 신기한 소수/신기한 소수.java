import java.io.*;

public class Main {
	static int N;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 1자리 소수부터 시작 (2, 3, 5, 7)
        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
    }

    public static void dfs(int num, int depth) {
        if (depth == N) {
            System.out.println(num);
            return;
        }

        for (int i = 1; i <= 9; i += 2) { // 짝수 제외 (2, 4, 6, 8)
            int nextNum = num * 10 + i;
            if (isPrime(nextNum)) {
                dfs(nextNum, depth + 1);
            }
        }
    }

    public static boolean isPrime(int k) {
        for (int i = 2; i * i <= k; i++) { // 제곱근까지만 확인
            if (k % i == 0) {
                return false;
            }
        }
        return true;
    }
}