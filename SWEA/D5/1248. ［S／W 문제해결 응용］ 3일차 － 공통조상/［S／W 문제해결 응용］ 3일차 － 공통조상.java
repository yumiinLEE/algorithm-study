import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer>[] tree;
    static int[] parent;
    static int subtreeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 수
            int E = Integer.parseInt(st.nextToken()); // 간선 수
            int n1 = Integer.parseInt(st.nextToken()); // 공통 조상을 찾을 노드 1
            int n2 = Integer.parseInt(st.nextToken()); // 공통 조상을 찾을 노드 2

            tree = new ArrayList[V + 1];
            parent = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                tree[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                tree[p].add(c);
                parent[c] = p;
            }

            // 가장 가까운 공통 조상 찾기
            int ancestor = lca(n1, n2);

            // 해당 조상을 루트로 하는 서브트리 크기 계산
            subtreeSize = 0;
            dfs(ancestor);

            // 결과 출력
            System.out.println("#" + t + " " + ancestor + " " + subtreeSize);
        }
    }

    // 가장 가까운 공통 조상(LCA) 찾기
    private static int lca(int a, int b) {
        Set<Integer> set = new HashSet<>();

        while (a != 0) {
            set.add(a);
            a = parent[a];
        }

        while (b != 0) {
            if (set.contains(b)) return b;
            b = parent[b];
        }

        return 1; // 루트
    }

    // DFS로 서브트리 크기 구하기
    private static void dfs(int node) {
        subtreeSize++;
        for (int child : tree[node]) {
            dfs(child);
        }
    }
}