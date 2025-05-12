import java.io.*;

public class Main {
    static int N;
    static int[] start, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전구의 개수
        N = Integer.parseInt(br.readLine());

        start = new int[N];  // 현재 전구 상태 저장
        target = new int[N]; // 목표 전구 상태 저장

        String line1 = br.readLine();
        String line2 = br.readLine();

        for (int i = 0; i < N; i++) {
            start[i] = line1.charAt(i) - '0'; // 문자열을 정수로 변환
            target[i] = line2.charAt(i) - '0';
        }

        int res1 = simulate(false); // 첫 번째 스위치를 누르지 않음
        int res2 = simulate(true);  // 첫 번째 스위치를 누름

        if (res1 == -1 && res2 == -1) {
            System.out.println(-1);
        } else if (res1 == -1) {
            System.out.println(res2);
        } else if (res2 == -1) {
            System.out.println(res1);
        } else {
            System.out.println(Math.min(res1, res2));
        }
    }

    static int simulate(boolean pressFirst) {
        int[] bulb = new int[N];
        for (int i = 0; i < N; i++) {
            bulb[i] = start[i]; // start 배열의 값을 bulb 배열로 복사
        }

        // 스위치 누른 횟수
        int count = 0;

        // 첫 번째 스위치를 눌러서 상태 변경
        if (pressFirst) {
            toggle(bulb, 0); // 첫 번째 전구와 두 번째 전구 상태를 반전
            count++;
        }

        // 두 번째 전구부터 마지막 전구까지 상태를 맞추기 위해 스위치를 누름
        for (int i = 1; i < N; i++) {
            // 현재 전구 상태가 목표 상태와 다르면 스위치를 눌러서 변경
            if (bulb[i - 1] != target[i - 1]) {
                toggle(bulb, i);
                count++;
            }
        }

        // 최종적으로 모든 전구 상태가 목표 상태와 일치하는지 확인
        for (int i = 0; i < N; i++) {
            if (bulb[i] != target[i]){
                return -1;                
            }
        }

        return count;
    }

    // 스위치를 눌렀을 때, i-1, i, i+1 전구의 상태를 변경
    static void toggle(int[] bulb, int i) {
        for (int j = i - 1; j <= i + 1; j++) {
            if (j >= 0 && j < N) {
                bulb[j] = 1 - bulb[j];
            }
        }
    }
}