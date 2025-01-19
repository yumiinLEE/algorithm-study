import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        // BufferedReader 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스의 개수 T
        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 반복
        for (int t = 1; t <= T; t++) {
            // 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());  // A사 1리터당 요금
            int Q = Integer.parseInt(st.nextToken());  // B사 기본 요금
            int R = Integer.parseInt(st.nextToken());  // B사 기본 사용량
            int S = Integer.parseInt(st.nextToken());  // B사 초과 요금 1리터당
            int W = Integer.parseInt(st.nextToken());  // 사용량

            // A사 요금 계산
            int A_bill = P * W;

            // B사 요금 계산
            int B_bill;
            if (W <= R) {
                B_bill = Q;  // 초과량이 없으면 기본 요금만
            } else {
                B_bill = Q + (W - R) * S;  // 초과량에 대해 요금 추가
            }

            // 더 저렴한 요금 계산
            int result = Math.min(A_bill, B_bill);

            // 결과 출력
            System.out.println("#" + t + " " + result);
        }
    }
}
