import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] denominations = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int amount = Integer.parseInt(st.nextToken());
            System.out.println("#" + t);
            
            for (int denomination : denominations) {
                int count = amount / denomination;
                amount %= denomination;
                System.out.print(count + " ");
            }
            System.out.println();
        }
    }
}