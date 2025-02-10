import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long left = 1, right = (long) N * N, answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = countLessOrEqual(mid, N);

            if (count >= k) {
                answer = mid;
                right = mid - 1;  // 더 작은 값이 있을 수 있음
            } else {
                left = mid + 1;   // 더 큰 값이 필요함
            }
        }

        bw.write(String.valueOf(answer) + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    private static long countLessOrEqual(long x, int N) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, x / i); // i번째 행에서 x 이하의 원소 개수
        }
        return count;
    }
}
