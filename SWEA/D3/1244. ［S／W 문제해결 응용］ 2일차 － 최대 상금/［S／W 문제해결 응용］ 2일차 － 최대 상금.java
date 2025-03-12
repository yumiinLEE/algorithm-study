import java.io.*;
import java.util.*;

public class Solution {
	static String[] num;
	static int cnt;  // 교환 횟수
	static int max;  // 최대 금액을 저장
	static Set<String> visited;  // 이미 방문한 숫자판을 저장할 Set

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		 
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			num = st.nextToken().split("");
			cnt = Integer.parseInt(st.nextToken());
			
			max = Integer.MIN_VALUE;
			
			visited = new HashSet<>();
			dfs(0);  // DFS로 교환을 시작 (교환 횟수는 0부터 시작)
			
			System.out.println("#" + t + " " + max);
		}
	}

	
	static void dfs(int n) {
	    // 교환 횟수가 cnt에 도달했을 때 최대 금액을 갱신
	    if (n == cnt) {
	        max = Math.max(max, Integer.parseInt(String.join("", num)));
	        return;
	    }
		
		// 가능한 모든 두 숫자판 위치를 선택하여 교환
		for (int i = 0; i < num.length; i++) {
			for (int j = i+1; j < num.length; j++) {
				// i번과 j번의 숫자판을 교환
				swap(i, j);
				
				String result = String.join("", num);
				// 중복된 숫자는 방문하지 않도록 Set에 저장
				if (!visited.contains(result + "-" + n)) {
					visited.add(result + "-" + n);
					dfs(n+1);
				}
				
				// 교환을 되돌려서 백트래킹
				swap(i, j);
			}
		}
	}

	static void swap(int i, int j) {
		String temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
}