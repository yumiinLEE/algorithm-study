package algo_study;

import java.io.*;
import java.util.*;

/*
SWEA B형 특강, 수열 편집
https://swexpertacademy.com/main/code/codeBattle/problemDetail.do?contestProbId=AX5MBiQqAbQDFASv&categoryId=AZTS_npqcGvHBINJ&categoryType=BATTLE&battleMainPageIndex=2#none
 
 - N개의 자연수로 이루어진 수열이 주어진다.
 - M번의 편집 명령 (I, D, C)을 순서대로 수행한다.
    I idx num: idx 앞에 num을 삽입
    D idx: idx 위치를 삭제
    C idx num: idx 위치 값을 num으로 교체
 - 편집이 끝난 후 인덱스 L에 위치한 수를 출력하되, 해당 인덱스가 존재하지 않으면 -1 출력
*/

public class SWEA_수열편집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                if (command.equals("I")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    list.add(idx, num);
                } else if (command.equals("D")) {
                    int idx = Integer.parseInt(st.nextToken());
                    list.remove(idx);
                } else if (command.equals("C")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    list.set(idx, num);
                }
            }
            
            int result;
            if (L >= 0 && L < list.size()) {
                result = list.get(L);
            } else {
                result = -1;
            }
            
            System.out.println("#" + t + " " + result);
        }
    }
}