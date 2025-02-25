import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int ones = 0, zeros = 0;
        
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                positive.add(num);
            } else if (num == 1) {
                ones++;
            } else if (num == 0) {
                zeros++;
            } else {
                negative.add(num);
            }
        }

        // 양수는 내림차순 정렬 (큰 값부터 곱해야 최대 합)
        Collections.sort(positive, Collections.reverseOrder());
        // 음수는 오름차순 정렬 (절댓값이 큰 것끼리 묶기)
        Collections.sort(negative);
        
        int sum = 0;

        // 양수 묶기
        for (int i = 0; i < positive.size() - 1; i += 2) {
            sum += positive.get(i) * positive.get(i + 1);
        }
        if (positive.size() % 2 == 1) {
            sum += positive.get(positive.size() - 1);
        }

        // 음수 묶기
        for (int i = 0; i < negative.size() - 1; i += 2) {
            sum += negative.get(i) * negative.get(i + 1);
        }
        if (negative.size() % 2 == 1) {
            if (zeros == 0) {
                sum += negative.get(negative.size() - 1);
            }
        }

        // 1은 묶지 않고 더함
        sum += ones;

        System.out.println(sum);
    }
}
