package algo_study;

import java.io.*;
import java.util.*;

/*
SWEA 10726 D3, 이진수 표현
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXRSXf_a9qsDFAXS

- N, M이 주어질 때 M의 마지막 N비트가 모두 1인지 판별
- 모두 1이면 "ON", 아니면 "OFF" 출력
*/


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int mask = (1 << N) - 1; // 마지막 N비트가 모두 1인 값

            // M의 마지막 N비트가 모두 1인지 체크
            if ((M & mask) == mask) {
                sb.append("#").append(t).append(" ON\n");
            } else {
                sb.append("#").append(t).append(" OFF\n");
            }
        }

        System.out.print(sb);
    }
}