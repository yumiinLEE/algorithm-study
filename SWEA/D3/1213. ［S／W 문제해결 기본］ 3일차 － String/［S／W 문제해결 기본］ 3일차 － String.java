import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	for (int t = 1; t <= 10; t++) {
    		int T = Integer.parseInt(br.readLine());
        	
    		String target = br.readLine();
    		String line = br.readLine();
    		
    		int cnt = 0;
    		for (int i = 0; i <= line.length()-target.length(); i++) {
				String word = line.substring(i, i+target.length());
				if (target.equals(word)) {
					cnt++;
				}
			}
    		System.out.println("#" + t + " " + cnt);
		}
    }
}
    	
