import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static boolean[] visited;
    static int[][] synergy;
    static int minDiff;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            synergy = new int[N][N];
            visited = new boolean[N];
            minDiff = Integer.MAX_VALUE;
 
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(line[j]);
                }
            }
 
            dfs(0, 0);
            System.out.println("#" + t + " " + minDiff);
        }
    }
 
    static void dfs(int depth, int start) {
        if (depth == N / 2) {
            calculateDifference();
            return;
        }
 
        for (int i = start; i < N; i++) {
            visited[i] = true;
            dfs(depth + 1, i + 1); 
            visited[i] = false;
        }
    }
 
    static void calculateDifference() {
        int aSum = 0;
        int bSum = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    aSum += synergy[i][j] + synergy[j][i];
                } else if (!visited[i] && !visited[j]) {
                    bSum += synergy[i][j] + synergy[j][i];
                }
            }
        }
 
        int diff = Math.abs(aSum - bSum);
        minDiff = Math.min(minDiff, diff);
    }
}