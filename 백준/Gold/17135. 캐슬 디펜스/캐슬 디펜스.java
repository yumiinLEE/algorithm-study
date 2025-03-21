/*
testcase
(가장 왼쪽에 있는 적을 선택하는 조건이 있어야 통과)
4 4 3
0 1 1 0
0 0 1 1
1 0 1 0
1 1 1 0
*/


import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D;  // N: 격자판의 행 수, M: 열 수, D: 궁수의 공격 범위
	static int[][] matrix;
	static int[] archers = new int[3];  // 궁수들의 위치
	static int maxKillCnt;  // 최대 제거된 적의 수
	static Set<Position> pos;  // 궁수들이 공격할 적들을 저장
	
	static class Position {
		int x;
		int y;
		
		Position (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        dfs(0, 0);
        
        System.out.println(maxKillCnt);
    }

    
    // 궁수의 위치를 조합하는 함수
	static void dfs(int n, int start) {
		// 궁수 3명이 모두 배치되었으면 시뮬레이션 실행
		if (n == 3) {
			simulateGame();
			return;
		}
		
		for (int i = start; i < M; i++) {
			archers[n] = i;
			dfs(n+1, i+1);
		}	
	}


	// 게임 시뮬레이션 함수
	private static void simulateGame() {
		
		// 배열 복사
		int[][] copymatrix = new int[N][M];
		
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
            	copymatrix[i][j] = matrix[i][j];
            }
        }
        
		int killCnt = 0;  // 이 게임에서 제거한 적의 수
		pos = new HashSet<>();
		
		while (true) {
			pos.clear();  // 매 턴마다 공격한 적 리스트 초기화
			boolean gameOver = true;  // 게임 종료 여부
			
			// 1. 각 궁수가 공격할 적을 찾고, 해당 적을 pos에 추가
			for (int archer : archers) {
				attack(copymatrix, archer);
			}
			
			// 2. 공격한 적을 제거
			for (Position p : pos) {
				if (copymatrix[p.x][p.y] == 1) {
                    copymatrix[p.x][p.y] = 0;
                    killCnt++;
                }
            }
			
			// 3. 적을 한 칸 아래로 이동
			for (int i = N - 1; i >= 1; i--) {
			    for (int j = 0; j < M; j++) {
			        copymatrix[i][j] = copymatrix[i - 1][j];
			    }
			}

            // 첫 번째 행은 초기화
            for (int j = 0; j < M; j++) {
                copymatrix[0][j] = 0;
            }
            
            // 4. 적이 더 이상 남지 않으면 게임 종료
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (copymatrix[i][j] == 1) {
                        gameOver = false;
                        break;
                    }
                }
            }

            if (gameOver) break;  // 적이 모두 제거되었거나 성에 도달했으면 종료
        }
		
		// 최대 제거 적의 수 갱신
		maxKillCnt = Math.max(maxKillCnt, killCnt);
	}


	// 궁수가 공격할 적을 찾는 함수
	static void attack(int[][] copymatrix, int archer) {
	    Position target = null;  // 공격할 적을 저장

	    // 적을 찾기: 아래에서 위로 탐색
	    for (int i = N - 1; i >= 0; i--) {
	        for (int j = 0; j < M; j++) {
	        	
	        	// 적이 있고, 공격 범위 내에 있는지 확인
	            if (copymatrix[i][j] == 1 && Math.abs(N - i) + Math.abs(archer - j) <= D) {
	            	// 가장 가까운 적을 선택, 거리가 같다면 가장 왼쪽에 있는 적을 선택
	                if (target == null || 
	                    Math.abs(N - i) + Math.abs(archer - j) < Math.abs(N - target.x) + Math.abs(archer - target.y) || 
	                    (Math.abs(N - i) + Math.abs(archer - j) == Math.abs(N - target.x) + Math.abs(archer - target.y) && j < target.y)) {
	      
	                    target = new Position(i, j);
	                }
	            }
	        }
	    }

	    // 공격할 적이 있으면 pos 집합에 추가
	    if (target != null) {
	        pos.add(target);
	    }
	}
}