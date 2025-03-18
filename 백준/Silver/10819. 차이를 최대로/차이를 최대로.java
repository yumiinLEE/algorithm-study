import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int[] visited;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		visited = new int[N];
		Arrays.fill(visited, -1);
		
		max = 0;
		dfs(0);
		
		System.out.println(max);
	}

	
	static void dfs(int n) {
		if (n == N) {
			max = Math.max(max, cal());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i] == -1) {
				visited[i] = n;
				dfs(n+1);
				visited[i] = -1;
			}
		}
	}

	static int cal() {
		int ans = 0;
		
		for (int i = 0; i < N-1; i++) {
			ans += Math.abs(arr[visited[i]] - arr[visited[i+1]]);
		}
		
		return ans;
	}
}