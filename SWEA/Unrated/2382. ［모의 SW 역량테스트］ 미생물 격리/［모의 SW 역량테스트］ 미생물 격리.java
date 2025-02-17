import java.io.*;
import java.util.*;

// 미생물 군집 정보를 저장하는 클래스
class Group {
    int num, d, max;

    public Group(int num, int d) {
        this.num = num; // 현재 미생물 수
        this.d = d; // 이동 방향
        this.max = num;  // 해당 군집의 최대 미생물 수
    }
}

public class Solution {
    // 이동 방향을 나타내는 배열
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
            int K = Integer.parseInt(st.nextToken()); // 초기 미생물 군집 개수
            
            // 미생물 군집 정보를 저장할 2차원 리스트 배열
            List<Group>[][] matrix = new ArrayList[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = new ArrayList<>();
                }
            }

            // 초기 미생물 군집 정보 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken()); // 미생물 수
                int d = Integer.parseInt(st.nextToken()); // 이동 방향

                matrix[x][y].add(new Group(num, d));
            }

            // M초 동안 미생물 이동 및 합치기 진행
            for (int time = 0; time < M; time++) {
                // 이동 후 새로운 군집 정보를 저장할 배열
                List<Group>[][] nextMatrix = new ArrayList[N][N];  
                
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        nextMatrix[i][j] = new ArrayList<>();
                    }
                }
                
                // 미생물 이동 처리
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!matrix[i][j].isEmpty()) {  // 미생물이 있으면
                            for (Group g : matrix[i][j]) {
                                int nx = i + dx[g.d]; // 새로운 위치 행 좌표
                                int ny = j + dy[g.d]; // 새로운 위치 열 좌표

                                // 가장자리에 도착한 경우 (약품이 있는 곳)
                                if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) { 
                                    g.num /= 2; // 미생물 수 절반 감소
                                    if (g.num > 0) { // 남아 있는 경우에만 방향 변경 후 이동
                                        if (g.d == 1) g.d = 2; // 상 -> 하
                                        else if (g.d == 2) g.d = 1; // 하 -> 상
                                        else if (g.d == 3) g.d = 4; // 좌 -> 우
                                        else g.d = 3; // 우 -> 좌
                                        nextMatrix[nx][ny].add(new Group(g.num, g.d));
                                    }
                                } else { // 일반 이동
                                    nextMatrix[nx][ny].add(new Group(g.num, g.d));
                                }
                            }
                        }
                    }
                }

                // 같은 위치에 있는 미생물 군집 합치기
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (nextMatrix[i][j].size() > 1) { // 여러 군집이 한 셀에 모인 경우
                            int totalNum = 0, maxNum = 0, newDir = 0;

                            // 가장 많은 미생물 수를 가진 군집의 방향으로 설정
                            for (Group g : nextMatrix[i][j]) {
                                totalNum += g.num;
                                if (g.num > maxNum) {
                                    maxNum = g.num;
                                    newDir = g.d;
                                }
                            }
                            nextMatrix[i][j].clear(); // 기존 군집 삭제
                            nextMatrix[i][j].add(new Group(totalNum, newDir)); // 합쳐진 군집 추가
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