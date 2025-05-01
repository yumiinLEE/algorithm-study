import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static int[][] matrix;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int startx, starty, endx, endy;
    static boolean[][] visited;
     
    static class Swirl {
        int x;
        int y;
        int time;
         
        Swirl (int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
             
            matrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            startx = Integer.parseInt(st.nextToken());
            starty = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            endx = Integer.parseInt(st.nextToken());
            endy = Integer.parseInt(st.nextToken());
             
            visited = new boolean[N][N];
            int result = bfs(startx, starty, 0);
             
            System.out.println("#" + t + " " + result);
        }
    }
 
    public static int bfs(int x, int y, int time) {
        Queue<Swirl> q = new LinkedList<>();
         
        q.add(new Swirl(x, y, time));
        visited[x][y] = true;
         
        while (!q.isEmpty()) {
            Swirl current = q.poll();
             
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                 
                if (nx == endx && ny == endy) {
                    return current.time + 1;
                }
                 
                if (0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
                    if (matrix[nx][ny] == 0) {
                        q.add(new Swirl(nx, ny, current.time + 1));
                        visited[nx][ny] = true;
                    }
                     
                    else if (matrix[nx][ny] == 2) {
                        if ((current.time + 1) % 3 == 0) {
                            q.add(new Swirl(nx, ny, current.time + 1));
                            visited[nx][ny] = true;
                        }
                        else {
                            q.add(new Swirl(current.x, current.y, current.time + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}