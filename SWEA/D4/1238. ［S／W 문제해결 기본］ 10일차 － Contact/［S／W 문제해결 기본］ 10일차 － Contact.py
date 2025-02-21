import java.io.*;
import java.util.*;

public class Solution {
	static List<Integer>[] lst;
	static int[] visited;
	static int maxNum;
	static int lastVisited;
	
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int N = Integer.parseInt(st.nextToken());
        	int start = Integer.parseInt(st.nextToken());
        	
        	lst = new List[101];
        	
        	for (int i = 0; i < 101; i++) {
				lst[i] = new ArrayList<>();
			}
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < N/2; i++) {
        		int u = Integer.parseInt(st.nextToken());
        		int v = Integer.parseInt(st.nextToken());
        		
				lst[u].add(v);
			}
        	
        	visited = new int[101];
        	bfs(start);
        	
        	System.out.println("#" + t + " " + lastVisited);
        }
	}

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		
		lastVisited = 0;
		maxNum = 0;
		
		q.add(start);
		visited[start] = 1;
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			for (int n : lst[current]) {
				if (visited[n] == 0) {
					q.add(n);
					visited[n] = visited[current] + 1;
					
					if (visited[n]>maxNum | (visited[n]==maxNum && n>lastVisited)) {
						lastVisited = n;
						maxNum = visited[n];
					}
				}
			}
		}
	}
}
