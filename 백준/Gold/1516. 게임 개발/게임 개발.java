import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 건물의 개수
        
        int[] buildTime = new int[N + 1]; // 각 건물의 건설 시간
        int[] result = new int[N + 1]; // 각 건물의 최소 건설 완료 시간
        int[] inDegree = new int[N + 1]; // 진입 차수 (선행 건물 개수)
        List<Integer>[] graph = new ArrayList[N + 1]; // 건물 간의 의존 관계 그래프
        
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>(); // 그래프 초기화
        
        // 입력 처리
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken()); // 건설 시간 저장
            result[i] = buildTime[i]; // 기본적으로 건설 시간을 저장
            
            while (true) {
                int pre = Integer.parseInt(st.nextToken()); // 선행 건물 번호
                if (pre == -1) break; // -1이면 종료
                graph[pre].add(i); // 선행 건물이 완료된 후 현재 건물 건설 가능
                inDegree[i]++; // 진입 차수 증가
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        // 진입 차수가 0인 건물(즉, 선행 조건이 없는 건물)부터 시작
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        
        // 위상 정렬 수행
        while (!queue.isEmpty()) {
            int current = queue.poll(); // 현재 건물
            
            for (int next : graph[current]) { // 현재 건물이 선행 조건인 건물들
                result[next] = Math.max(result[next], result[current] + buildTime[next]); // 최소 건설 시간 갱신
                if (--inDegree[next] == 0) queue.add(next); // 선행 건물이 모두 지어진 경우 큐에 추가
            }
        }
        
        // 결과 출력
        for (int i = 1; i <= N; i++) System.out.println(result[i]);
    }
}