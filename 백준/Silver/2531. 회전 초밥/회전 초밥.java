import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] belt = new int[N];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int[] sushiCount = new int[d + 1]; // 초밥 종류별 개수 카운트
        int uniqueCount = 0;
        int maxVariety = 0;

        // 초기 윈도우 설정
        for (int i = 0; i < k; i++) {
            if (sushiCount[belt[i]] == 0) {
            	uniqueCount++;
            }
            sushiCount[belt[i]]++;
        }

        // 쿠폰 초밥 추가
        if (sushiCount[c] == 0) {
            maxVariety = uniqueCount + 1;
        } else {
            maxVariety = uniqueCount;
        }

        // 투포인터로 회전 초밥 계산
        for (int i = 1; i < N; i++) {
            int removeIdx = belt[i - 1]; // 제거할 접시
            int addIdx = belt[(i + k - 1) % N]; // 새로 추가할 접시

            // 이전 접시 제거
            if (--sushiCount[removeIdx] == 0) {
            	uniqueCount--;
            }

            // 새로운 접시 추가
            if (sushiCount[addIdx]++ == 0) {
            	uniqueCount++;
            }

            // 쿠폰 초밥 추가
            if (sushiCount[c] == 0) {
                maxVariety = Math.max(maxVariety, uniqueCount + 1);
            } else {
                maxVariety = Math.max(maxVariety, uniqueCount);
            }
        }

        System.out.println(maxVariety);
    }
}