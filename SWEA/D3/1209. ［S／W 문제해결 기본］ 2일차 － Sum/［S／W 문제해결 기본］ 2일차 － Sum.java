import java.io.*;
import java.util.*;

public class Solution {
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine());
            
        	int[][] matrix = new int[100][100];
        	
        	for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
        	
        	int max = 0;
        	
        	int rightsum = 0;
        	int leftsum = 0;
        	
        	for (int i = 0; i < 100; i++) {
        		int rowsum = 0;
        		int colsum = 0;
				for (int j = 0; j < 100; j++) {
					
					// 가로 합
					rowsum += matrix[i][j];
					
					// 세로합
					colsum += matrix[j][i];
				}
					
				max = Math.max(max, Math.max(rowsum, colsum));
				
				// 오른쪽 대각선
				rightsum += matrix[i][i];
				
				// 왼쪽 대각선
				leftsum += matrix[i][99-i];
				}
        	
        	max = Math.max(max, Math.max(rightsum, leftsum));
        	
        	System.out.println("#" + T + " " + max);
        }
	}
}