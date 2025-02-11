import java.io.*;
import java.util.*;

public class Solution {
    static int N;          // 무게추 개수
    static int cnt;        // 가능한 경우의 수
    static int[] arr;      // 무게추 배열
    static boolean[] visited; // 특정 무게추를 사용했는지 여부 확인

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
        
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine()); // 무게추 개수 입력
            arr = new int[N];
            visited = new boolean[N];
            cnt = 0;
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); // 무게추 값 입력
			}
            
            // DFS 탐색 시작 (모든 무게추를 배치하는 모든 경우 탐색)
            dfs(0, 0, 0);
            
            System.out.println("#" + t + " " + cnt);
		}
    }

    /**
     * 깊이 우선 탐색(DFS)으로 모든 경우 탐색
     * 
     * @param n        현재까지 배치한 무게추 개수
     * @param rightsum 오른쪽 저울에 올린 무게의 합
     * @param leftsum  왼쪽 저울에 올린 무게의 합
     */
	public static void dfs(int n, int rightSum, int leftSum) {
		// 모든 무게추를 다 배치한 경우 -> 유효한 경우의 수 증가
        if (n == N) {
            cnt++;
            return;
        }

        // 아직 사용하지 않은 무게추를 하나 선택
        for (int i = 0; i < N; i++) {
            if (!visited[i]) { // 방문하지 않은 무게추라면 선택
                visited[i] = true;

                // 1. 왼쪽 저울에 추가하는 경우 (항상 가능)
                dfs(n + 1, rightSum, leftSum + arr[i]);

                // 2. 오른쪽 저울에 추가하는 경우 (단, 오른쪽이 왼쪽보다 커지지 않아야 함)
                if (rightSum + arr[i] <= leftSum) {
                    dfs(n + 1, rightSum + arr[i], leftSum);
                }

                // 백트래킹: 원상태로 되돌리기
                visited[i] = false;
            }
        }
    }
}

//package ssafy;
//
//import java.io.*;
//import java.util.*;
//
//public class Solution {
//	static int N;
//	static int cnt;
//	static int[] arr;
//	static boolean[] visited;
//	
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        
//        int testCase = Integer.parseInt(br.readLine());
//        
//        for (int t = 1; t <= testCase; t++) {
//            N = Integer.parseInt(br.readLine());
//            
//            cnt = 0;
//            
//            arr = new int[N];
//            visited = new boolean[N];
//            st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < N; i++) {
//				arr[i] = Integer.parseInt(st.nextToken());
//			}
//            dfs(0, 0, 0);
//            
//            System.out.println("#" + t + " " + cnt);
//		}
//    }
//
//	public static void dfs(int n, int rightsum, int leftsum) {
//		if (n == N) {
//			cnt++;
//			return;
//		}
//		for (int i = 0; i < N; i++) {
//			if (rightsum + arr[i] > leftsum && !visited[i]) {
//				visited[i] = true;
//				dfs(n+1, rightsum, leftsum + arr[i]);
//				visited[i] = false;
//			}
//			else if (rightsum + arr[i] <= leftsum && !visited[i]) {
//				visited[i] = true;
//				dfs(n+1, rightsum + arr[i], leftsum);
//				dfs(n+1, rightsum, leftsum + arr[i]);
//				visited[i] = false;
//			}
//		}
//	}
//}