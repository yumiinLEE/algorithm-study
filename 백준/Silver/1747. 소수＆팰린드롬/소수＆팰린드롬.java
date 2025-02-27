import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        while (true) {
            if (isPrime(N) && isPalindrome(N)) {
                System.out.println(N);
                break;
            }
            N++;
        }
    }

    // 소수 판별 함수
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 팰린드롬 판별 함수
    private static boolean isPalindrome(int n) {
        String str = Integer.toString(n);
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
