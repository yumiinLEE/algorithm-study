package algo_study;

import java.io.*;

/*
SWEA 1288 D2, 새로운 불면증 치료법
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18_yw6I9MCFAZN

- N의 배수들을 계속해서 세며, 등장한 숫자들을 기록
- 0~9까지 모든 숫자를 확인하는 순간 종료
- 최소 몇 번째 배수를 셌을 때 모든 숫자를 확인하게 되는지 출력
*/

public class SWEA_D2_1288 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        int total = (1 << 10) - 1;  // 0~9까지 모든 숫자가 등장한 상태: 0b1111111111 = 1023

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());

            int visited = 0;  // 비트마스크 초기화
            int count = 0;

            while (visited != total) {
                count++;
                
                // 1: 나눗셈 연산을 통한 자릿수 추출
                int current = N * count;

                while (current > 0) {
                    int digit = current % 10;
                    visited |= (1 << digit);
                    current /= 10;
                }
                
                // 2: 문자열 변환 후 처리
//                char[] arr = String.valueOf(N * count).toCharArray();
//
//                for (char c : arr) {
//                    int num = c - '0';
//                    visited |= (1 << num);
//                }
            }

            System.out.println("#" + i + " " + (N * count));
        }
    }
}