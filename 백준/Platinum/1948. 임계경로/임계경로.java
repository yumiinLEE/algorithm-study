import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 도로의 개수
        
        List<int[]>[] graph = new ArrayList[n + 1]; // 방향 그래프
        List<int[]>[] reverseGraph = new ArrayList[n + 1]; // 역방향 그래프 (Critical Path 탐색용)
        int[] inDegree = new int[n + 1]; // 진입 차수
        int[] maxTime = new int[n + 1]; // 최장 경로 시간 저장
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            
            graph[start].add(new int[]{end, time}); // 정방향 그래프 저장
            reverseGraph[end].add(new int[]{start, time}); // 역방향 그래프 저장
            inDegree[end]++; // 진입 차수 증가
        }
        
        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken()); // 출발 도시
        int endCity = Integer.parseInt(st.nextToken()); // 도착 도시
        
        // 위상 정렬을 이용한 최장 경로 탐색
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startCity);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int[] next : graph[current]) {
                int nextCity = next[0];
                int travelTime = next[1];
                
                maxTime[nextCity] = Math.max(maxTime[nextCity], maxTime[current] + travelTime);
                
                if (--inDegree[nextCity] == 0) {
                    queue.add(nextCity);
                }
            }
        }
        
        System.out.println(maxTime[endCity]); // 최장 경로 시간 출력
        
        // 역방향 그래프를 이용한 Critical Path 찾기
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> reverseQueue = new ArrayDeque<>();
        reverseQueue.add(endCity);
        int criticalRoads = 0;
        
        while (!reverseQueue.isEmpty()) {
            int current = reverseQueue.poll();
            
            for (int[] prev : reverseGraph[current]) {
                int prevCity = prev[0];
                int travelTime = prev[1];
                
                if (maxTime[current] - maxTime[prevCity] == travelTime) { // Critical Path 조건
                    criticalRoads++;
                    if (!visited[prevCity]) {
                        visited[prevCity] = true;
                        reverseQueue.add(prevCity);
                    }
                }
            }
        }
        
        System.out.println(criticalRoads); // Critical Path 개수 출력
    }
}