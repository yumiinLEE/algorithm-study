import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        boolean[][] grid = new boolean[101][101];
        
        for (int i = 0; i < 4; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int x1 = Integer.parseInt(st.nextToken());
        	int y1 = Integer.parseInt(st.nextToken());
        	int x2 = Integer.parseInt(st.nextToken());
        	int y2 = Integer.parseInt(st.nextToken());
        	
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    grid[x][y] = true;
                }
            }
        }
        
        int area = 0;
        for (int x = 1; x <= 100; x++) {
            for (int y = 1; y <= 100; y++) {
                if (grid[x][y]) {
                    area++;
                }
            }
        }
        
        System.out.println(area);
    }
}
