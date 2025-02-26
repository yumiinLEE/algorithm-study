import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] meetings = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 끝나는 시간
        }
        
        // 끝나는 시간을 기준으로 정렬, 끝나는 시간이 같다면 시작 시간을 기준으로 정렬
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        
        int count = 0;
        int endTime = 0;
        
        for (int i = 0; i < N; i++) {
            if (meetings[i][0] >= endTime) { // 현재 회의가 이전 회의와 겹치지 않는다면
                endTime = meetings[i][1]; // 회의 끝나는 시간 갱신
                count++; // 회의 개수 증가
            }
        }
        
        System.out.println(count); // 최대 사용할 수 있는 회의 개수 출력
    }
}
