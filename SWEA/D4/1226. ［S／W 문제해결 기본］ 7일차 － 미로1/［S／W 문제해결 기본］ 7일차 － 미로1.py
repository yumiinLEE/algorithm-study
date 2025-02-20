import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] matrix;
    static Queue<int[]> q;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
     
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        for (int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine());
             
            matrix = new int[16][16];
             
            for (int i = 0; i < 16; i++) {
                String line[] = br.readLine().split("");
                for (int j = 0; j < 16; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
            q = new LinkedList<>();
                             
            System.out.println("#" + t + " " + bfs(2, 2));
        }
    }
 
    public static int bfs(int x, int y) {
        q.add(new int[] {x, y});
         
        while (!q.isEmpty()) {
            int[] currnet = q.poll();
             
            for (int i = 0; i < 4; i++) {
                int nx = currnet[0] + dx[i];
                int ny = currnet[1] + dy[i];
                 
                if (matrix[nx][ny]==3) {
                    return 1;
                }
                 
                if (nx>=0 && nx<16 && ny>=0 && ny<16 && matrix[nx][ny]==0) {
                    q.add(new int[] {nx, ny});
                    matrix[nx][ny] = 1;
                }
            }
        }
        return 0;
    }
}