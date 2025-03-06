// 우성현님 코드 참고함

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    // 부모를 찾을 때까지
    private static int find(int x) {
        while(x != parent[x]) { // x가 부모와 다르면 (즉, 루트가 아니라면)
	            parent[x] = parent[parent[x]]; // 할아버지로 설정
	            x = parent[x];// 부모로 설정 (x를 한 단계 위로 올림)
        }
        return x; // 최종적으로 루트 노드 반환
    }

    // 집합 합치기
    private static void union(int x, int y) {
        x = find(x); // x가 속한 집합의 루트 노드 찾기
        y = find(y); // y가 속한 집합의 루트 노드 찾기

        if (x != y) {  // 두 원소가 다른 집합에 속해 있을 때만 합친다.
            if (x < y) parent[y] = x;  // 더 작은 번호의 루트를 기준으로 병합
            else parent[x] = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());   // 명령의 수

        // 기본 셋팅
        parent = new int[N+1];
        for (int i =1; i <= N; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) union(a,b);
            else if (cmd == 1) {
                if (find(a) == find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}