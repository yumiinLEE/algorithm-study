import java.io.*;

public class Solution {
	static String[] map = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			String[] num = br.readLine().split(" ");
			int N = Integer.parseInt(num[0]);
			int M = Integer.parseInt(num[1]);
			
			int[][] matrix = new int[N][M];
			
            // 암호 코드의 시작점을 찾기 위한 변수
			boolean flag = true;
			int startN = 0;
			int startM = 0;
			
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split("");
				for (int j = M-1; j >=0; j--) {
                    // 각 자리의 숫자를 배열에 저장 (역순으로 처리)
					matrix[i][j] = Integer.parseInt(line[j]);
					
                    // 첫 번째 1을 찾으면 그 위치를 기록하고, flag를 false로 설정하여 더 이상 찾지 않도록 함
					if (matrix[i][j] == 1 && flag) {
						startN = i;
						startM = j;
						flag = false;
					}
				}
			}
            
			// 56비트 암호 코드 추출
			int[] stringCode = new int[56];
			int k = 0;
			for (int j = startM - 56 + 1; j <= startM; j++) {
				stringCode[k] = matrix[startN][j];
				k++;
			}
			
			// 암호 코드를 숫자 배열로 변환
			int[] code = new int[8];
			for (int i = 0; i < 8; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j <7; j++) {
					sb.append(stringCode[i * 7 + j]);
				}
				
				String segment = sb.toString();
                
                // map에서 일치하는 숫자 찾기
				for (int j = 0; j < 10; j++) {
					if (segment.equals(map[j])) {
						code[i] = j;
						break;
					}
				}
			}
			
            // 홀수 자리와 짝수 자리 숫자의 합을 계산
			int even = 0;
			int odd = 0;
			for (int i = 0; i < 8; i++) {
				if (i % 2 == 0) {
					odd += code[i];
				} 
                else {
					even += code[i];
				}
			}
			
            // (홀수자리 합 x 3) + 짝수자리 합이 10의 배수인지 체크
			if ((odd*3 + even) % 10 == 0) {
				System.out.println("#" + t + " " + (odd+even));
			}
			else {
				System.out.println("#" + t + " " + 0);
			}
		}
	}
}