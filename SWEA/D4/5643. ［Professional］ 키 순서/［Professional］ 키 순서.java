import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int M;
    static boolean[] visited;
    static LinkedList<Integer>[] lst_child;
    static LinkedList<Integer>[] lst_parnet;
    static Queue<Integer> q;
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            lst_child = new LinkedList[N+1];
            lst_parnet = new LinkedList[N+1];
            for (int i = 1; i <= N; i++) {
            	lst_child[i] = new LinkedList<>();
                lst_parnet[i] = new LinkedList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                lst_child[b].add(a);
                lst_parnet[a].add(b);
            }
            
            int cnt = 0;
            
            q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
            	visited = new boolean[N+1];
            	
				bfs(i, lst_child);
				bfs(i, lst_parnet);
				
				for (int j = 1; j <= N; j++) {
					if (!visited[j]) {
						break;
					}
					if (j == N) {
						cnt++;
					}
				}
			}
            System.out.println("#" + t + " " + cnt);
        }
    }

	public static void bfs(int student, LinkedList<Integer>[] lst) {
		q.add(student);
		visited[student] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for (int s : lst[current]) {
				if (!visited[s]) {
					q.add(s);
					visited[s] = true;
				}
			}
		}
	}
}