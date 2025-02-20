import java.io.*;
import java.util.*;

public class Solution {
   
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String pass = st.nextToken();
            
            Stack<Character> stack = new Stack<>();
            
            for (int i = 0; i < N; i++) {
            	char current = pass.charAt(i);
    			if (!stack.isEmpty() && stack.peek().equals(current)) {
    				stack.pop();
    			}
    			else {
    				stack.add(current);
    			}
    		}
            
            StringBuilder sb = new StringBuilder();
            
            while (!stack.isEmpty()) {
            	sb.insert(0, stack.pop());
            }
            System.out.println("#" + t + " " + sb.toString());
		}
    }
}