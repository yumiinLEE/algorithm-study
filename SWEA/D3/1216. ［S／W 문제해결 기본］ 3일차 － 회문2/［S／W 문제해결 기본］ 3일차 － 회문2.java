import java.io.*;

public class Solution {
	static char[][] matrix;
	static int cnt, cntEven; // 현재 회문의 길이 (회문이 짝수인 경우, 홀수인 경우 나누어서 계산)
	static int rowMax, colMax; // 각각 가로와 세로에서의 최대 회문 길이
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			
			matrix = new char[100][100];
			
			for (int i = 0; i < 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					matrix[i][j] = line.charAt(j);
				}
			}
			
			rowMax = 1;
			colMax = 1;
			ans = 1;  // 최종적으로 가장 긴 회문 길이를 기록
			
			// 글자판을 탐색하며 가로, 세로 각각에 대해 가장 긴 회문을 찾기
			for (int i = 0; i < 100; i++) {
				for (int j = 1; j < 100; j++) {
					
					// 가로 방향에서 회문 계산
					cal(i, j, true);
					rowMax = Math.max(rowMax, cnt);
					
					// 세로 방향에서 회문 계산
					cal(i, j, false);
					colMax = Math.max(colMax, cnt);
					
					ans = Math.max(ans, Math.max(rowMax, colMax));
				}
			}
			
			System.out.println("#" + T + " " + ans);
		}
	}

	static void cal(int x, int y, boolean k) {
		
		cnt = 1;  // 최소 길이는 1
		cntEven = 0;
		
		// 가로 방향 회문 계산
		if (k) {
			// 회문 길이가 홀수인 경우
			int left = y-1;
			int right = y+1;
			
			// 양쪽 포인터가 범위 내에 있고, 양쪽 값이 같으면 회문 길이를 증가시킴
			while (0 <= left && right < 100 && matrix[x][left] == matrix[x][right]) {
				left--;
				right++;
				cnt+=2;
			}
			
			// 회문 길이가 짝수인 경우
			left = y;
			right = y+1;
			
			// 양쪽 포인터가 범위 내에 있고, 양쪽 값이 같으면 회문 길이를 증가시킴
			while (0 <= left && right < 100 && matrix[x][left] == matrix[x][right]) {
				left--;
				right++;
				cntEven+=2;
			}
			
			cnt = Math.max(cnt, cntEven);
		}
		
		// 세로 방향 회문 계산
		else {
			
			// 회문 길이가 홀수인 경우
			int left = x-1;
			int right = x+1;
			
			while (0 <= left && right < 100 && matrix[left][y] == matrix[right][y]) {
				left--;
				right++;
				cnt+=2;
			}
			
			// 회문 길이가 짝수인 경우
			left = x;
			right = x+1;
			
			while (0 <= left && right < 100 && matrix[left][y] == matrix[right][y]) {
				left--;
				right++;
				cntEven+=2;
			}
			
			cnt = Math.max(cnt, cntEven);
		}
	}
}