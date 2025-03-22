import java.io.*;
import java.util.*;

public class Main {
    static int N; // 배열의 크기
    static int r, c; // 찾고자 하는 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 2^N x 2^N 크기의 배열
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        // Z모양 탐색
        System.out.println(findZOrder((int) Math.pow(2, N), 0, 0, 0));
    }
    
    /*
     * Z 모양 탐색을 수행하는 재귀 함수
     * size 현재 탐색하는 정사각형의 한 변 길이
     * x 현재 정사각형의 시작 행 위치
     * y 현재 정사각형의 시작 열 위치
     * count 현재까지 방문한 칸의 개수
     */
    static int findZOrder(int size, int x, int y, int count) {
        // 크기가 1x1일 경우
        if (size == 1) {
            return count;
        }
        
        int newSize = size / 2; // 현재 정사각형을 4등분한 한변의 크기
        int offset = newSize * newSize; // 한 사분면이 차지하는 칸 개수
        
        // 찾고자 하는 위치(r, c)가 어느 사분면에 속하는지 판단하여 재귀 호출
        if (r < x + newSize && c < y + newSize) {
            // 1사분면: 좌표 변화 없이 그대로 탐색
            return findZOrder(newSize, x, y, count);
        } else if (r < x + newSize && c >= y + newSize) {
            // 2사분면: y를 newSize만큼 이동, 1사분면의 칸 개수를 더한 후 탐색
            return findZOrder(newSize, x, y + newSize, count + offset);
        } else if (r >= x + newSize && c < y + newSize) {
            // 3사분면: x를 newSize만큼 이동, 1, 2사분면의 칸 개수를 더한 후 탐색
            return findZOrder(newSize, x + newSize, y, count + 2 * offset);
        } else {
            // 4사분면: x와 y를 newSize만큼 이동, 1, 2, 3사분면의 칸 개수를 더한 후 탐색
            return findZOrder(newSize, x + newSize, y + newSize, count + 3 * offset);
        }
    }
}
