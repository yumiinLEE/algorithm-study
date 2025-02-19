import java.io.*;
import java.util.*;

public class Solution {
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) {
        	int N = Integer.parseInt(br.readLine());
        	
        	int[][] matrix = new int[N][N];
        	
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
        	
        	int cnt = 0;
        	for (int i = 0; i < N; i++) {
				boolean flag = false;
				
				for (int j = 0; j < N; j++) {
					if (matrix[j][i] == 1) {
						flag = true;
					}
					else if (matrix[j][i] == 2 && flag) {
						cnt++;
						flag = false;
					}
				}
			}
        	System.out.println("#" + t + " " + cnt);
        }
    }
}
        	
        	