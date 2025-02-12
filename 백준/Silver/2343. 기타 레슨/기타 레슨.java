import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        int N = Integer.parseInt(st.nextToken());  // 강의 수
        int M = Integer.parseInt(st.nextToken());  // 블루레이 개수
        int[] lectures = new int[N];

        st = new StringTokenizer(br.readLine());
        int maxLecture = 0;  // 가장 긴 강의 길이 (이분 탐색의 최소값)
        int sumLecture = 0;  // 전체 강의 길이 합 (이분 탐색의 최대값)

        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            maxLecture = Math.max(maxLecture, lectures[i]);
            sumLecture += lectures[i];
        }

        // 이분 탐색 시작
        int left = maxLecture, right = sumLecture;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (countBlurays(lectures, mid) <= M) {  // M개 이하라면 크기를 줄일 수 있음
                answer = mid;
                right = mid - 1;
            } else {  // M개를 초과하면 크기를 늘려야 함
                left = mid + 1;
            }
        }

        // 정답 출력
        System.out.println(answer);
    }

    // 블루레이 개수를 계산하는 메서드
    private static int countBlurays(int[] lectures, int size) {
        int count = 1;  // 첫 번째 블루레이 사용
        int total = 0;  // 현재 블루레이의 총 길이

        for (int lecture : lectures) {
            if (total + lecture > size) {  // 현재 블루레이에 더 넣을 수 없으면
                count++;  // 새로운 블루레이 사용
                total = lecture;  // 새로운 블루레이에 현재 강의 추가
            } else {
                total += lecture;  // 현재 블루레이에 강의 추가
            }
        }

        return count;  // 사용된 블루레이 개수 반환
    }
}
