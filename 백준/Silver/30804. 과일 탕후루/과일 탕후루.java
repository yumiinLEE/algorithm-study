import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] fruits = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[10];  // 과일 번호는 1~9
        int kind = 0, left = 0, max = 0;

        for (int right = 0; right < N; right++) {
            if (count[fruits[right]]++ == 0) {
                kind++;
            }

            while (kind > 2) {
                if (--count[fruits[left]] == 0) {
                    kind--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        System.out.println(max);
    }
}
