import java.io.*;
import java.util.*;

public class Solution {
	static List<Integer>[] lst;
	static Queue<Integer> q;
	static boolean[] visited;
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) {
        	st = new StringTokenizer(br.readLine());
        	int T = Integer.parseInt(st.nextToken());
        	int N = Integer.parseInt(st.nextToken());
        	
        	lst = new LinkedList[100];
        	for (int i = 0; i < 100; i++) {
        		lst[i] = new LinkedList<>();
			}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < N; i++) {
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	
            	lst[a].add(b);
			}
        	
        	q = new LinkedList<>();
        	visited = new boolean[100];
        	
        	System.out.println("#" + t + " " + bfs(0));
        }
    }

	public static int bfs(int node) {
		q.add(node);
		visited[node] = true;
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			if (current == 99) {
				return 1;
			}
			
			for (int next : lst[current]) {
				if (!visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
		}
		return 0;
	}
}
        	
        	