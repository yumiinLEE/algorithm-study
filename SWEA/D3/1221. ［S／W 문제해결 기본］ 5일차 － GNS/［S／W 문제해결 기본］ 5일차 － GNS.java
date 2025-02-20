import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 숫자 단어를 배열로 저장
        String[] numberWords = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
        
        // 숫자 단어의 개수를 세기위한 배열
        int[] count = new int[10];

        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            br.readLine(); // 읽고 버림
            String[] words = br.readLine().split(" "); // 단어를 배열로 저장
            
            // 개수 세기
            for (String word : words) {
                for (int i = 0; i < 10; i++) {
                    if (word.equals(numberWords[i])) {
                        count[i]++;
                        break;
                    }
                }
            }

            // 출력 만들기
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < 10; i++) {
                while (count[i]-- > 0) {
                    sb.append(numberWords[i]).append(" ");
                }
            }
            sb.append("\n");

            // 개수 배열 초기화
            count = new int[10];
        }

        // 한 번에 출력
        System.out.print(sb.toString());
    }
}