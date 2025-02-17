// 2382. [모의 SW 역량테스트] 미생물 격리

import java.io.*;
import java.util.*;

class Group {
    int num, d, max;

    public Group(int num, int d) {
        this.num = num;
        this.d = d;
        this.max = num;  // 최대 미생물 수 초기화
    }
}

public class Solution {
    static int[] dx = {0, -1, 1, 0, 0};  // (0: dummy), 상(1), 하(2), 좌(3), 우(4)
    static int[] dy = {0, 0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 구역 크기
            int M = Integer.parseInt(st.nextToken()); // 격리 시간
            int K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수
            
            List<Group>[][] matrix = new ArrayList[N][N]; // 새로운 군집 저장할 배열
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < K; i++) { // 미생물 정보 입력
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                matrix[x][y].add(new Group(num, d));
            }

            for (int time = 0; time < M; time++) { // M시간 동안 이동
                List<Group>[][] nextMatrix = new ArrayList[N][N];  // 이동 후 새로운 군집 배열
                
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        nextMatrix[i][j] = new ArrayList<>();
                    }
                }
                
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!matrix[i][j].isEmpty()) {
                            for (Group g : matrix[i][j]) {
                                int nx = i + dx[g.d];
                                int ny = j + dy[g.d];

                                if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) { 
                                    g.num /= 2; // 약품 셀에 도착하면 절반 소멸
                                    if (g.num > 0) { // 살아남은 경우만 이동 방향 반전
                                    	if (g.d == 1) {
                                    	    g.d = 2;
                                    	} else if (g.d == 2) {
                                    	    g.d = 1;
                                    	} else if (g.d == 3) {
                                    	    g.d = 4;
                                    	} else {
                                    	    g.d = 3;
                                    	}
                                        nextMatrix[nx][ny].add(new Group(g.num, g.d));
                                    }
                                } else { // 일반 이동
                                    nextMatrix[nx][ny].add(new Group(g.num, g.d));
                                }
                            }
                        }
                    }
                }

                // 군집 합치기
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (nextMatrix[i][j].size() > 1) { // 2개 이상의 군집이 같은 셀로 모이면
                            int totalNum = 0, maxNum = 0, newDir = 0;

                            for (Group g : nextMatrix[i][j]) {
                                totalNum += g.num;
                                if (g.num > maxNum) {
                                    maxNum = g.num;
                                    newDir = g.d;
                                }
                            }
                            nextMatrix[i][j].clear();
                            nextMatrix[i][j].add(new Group(totalNum, newDir));
                        }
                    }
                }

                // 현재 배열을 이동 후 배열로 업데이트
                matrix = nextMatrix;
            }

            // 남아 있는 미생물 총합 계산
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (Group g : matrix[i][j]) {
                        sum += g.num;
                    }
                }
            }

            // 결과 출력
            System.out.println("#" + t + " " + sum);
        }
    }
}
