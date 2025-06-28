package algo_study;

import java.io.*;
import java.util.*;

/*
SWEA 1230 D3, 암호문3
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14zIwqAHwCFAYD

-암호문 뭉치를 수정하는 프로그램을 작성한다.
-명령어는 3가지:
	  I x y s : x번째 다음에 y개의 암호문 s 삽입
	  D x y   : x번째 다음부터 y개의 암호문 삭제
	  A y s   : 맨 뒤에 y개의 암호문 s 추가
-수정 후 암호문 뭉치의 처음 10개를 출력한다.
*/



public class SWEA_D3_1230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            LinkedList<String> list = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                String cmd = st.nextToken();

                if (cmd.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    List<String> toInsert = new ArrayList<>();
                    for (int i = 0; i < y; i++) {
                        toInsert.add(st.nextToken());
                    }
                    list.addAll(x, toInsert);
                } else if (cmd.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        list.remove(x);
                    }
                } else if (cmd.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        list.add(st.nextToken());
                    }
                }
            }

            // 출력
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t);
            Iterator<String> iter = list.iterator();
            for (int i = 0; i < 10 && iter.hasNext(); i++) {
                sb.append(" ").append(iter.next());
            }
            System.out.println(sb);
        }
    }
}