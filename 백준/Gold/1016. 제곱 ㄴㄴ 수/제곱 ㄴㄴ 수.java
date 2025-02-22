import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long min = Long.parseLong(input[0]);
        long max = Long.parseLong(input[1]);
        
        // 범위 내 수들의 개수
        int count = (int)(max - min + 1);
        
        // 제곱ㄴㄴ수를 저장하는 배열 (초기값은 true)
        boolean[] isSquareFree = new boolean[count];
        for (int i = 0; i < count; i++) {
            isSquareFree[i] = true;
        }

        // 2 이상의 수들에 대해 제곱수 배수들을 false로 표시
        for (long i = 2; i * i <= max; i++) {
            long square = i * i;
            
            // min 이상에서 square의 첫 번째 배수가 어디서 시작할지 계산
            long start = Math.max(square * ((min + square - 1) / square), square);
            
            // start부터 시작해서 square씩 증가하며 범위 내 제곱수의 배수를 찾아 처리
            for (long j = start; j <= max; j += square) {
                // j - min을 통해, j가 min에서부터 몇 번째 인덱스인지 구함
                isSquareFree[(int)(j - min)] = false;
            }
        }

        // 제곱ㄴㄴ수의 개수를 셈
        int result = 0;
        for (boolean free : isSquareFree) {
            if (free) result++;
        }

        // 결과 출력
        System.out.println(result);
    }
}
