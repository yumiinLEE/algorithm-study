import java.io.*;
import java.util.*;

public class Main {
    static int N, M, fuel;
    static int[][] map;
    static boolean[][] visited;

    static int taxiX, taxiY; // 택시 위치

    // 좌표와 거리 정보를 저장하는 클래스
    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    // 승객 정보를 저장하는 클래스
    static class Passenger {
        int startX, startY, endX, endY;
        boolean picked; // 이미 태운 승객인지 여부

        Passenger(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.picked = false;
        }
    }

    static ArrayList<Passenger> passengers = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String[] nmk = br.readLine().split(" ");
        N = Integer.parseInt(nmk[0]);
        M = Integer.parseInt(nmk[1]);
        fuel = Integer.parseInt(nmk[2]);

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(row[j]); // 0: 빈칸, 1: 벽
            }
        }

        String[] taxiInfo = br.readLine().split(" ");
        taxiX = Integer.parseInt(taxiInfo[0]) - 1;
        taxiY = Integer.parseInt(taxiInfo[1]) - 1;

        // 승객 정보 입력
        for (int i = 0; i < M; i++) {
            String[] pInfo = br.readLine().split(" ");
            int sx = Integer.parseInt(pInfo[0]) - 1;
            int sy = Integer.parseInt(pInfo[1]) - 1;
            int ex = Integer.parseInt(pInfo[2]) - 1;
            int ey = Integer.parseInt(pInfo[3]) - 1;
            passengers.add(new Passenger(sx, sy, ex, ey));
        }

        // 승객 수만큼 반복
        for (int i = 0; i < M; i++) {
            // 가장 가까운 승객 찾기
            int[] result = findNearestPassenger();
            int distToPassenger = result[0];
            int index = result[1];

            // 승객이 없거나 연료 부족하면 종료
            if (index == -1 || fuel < distToPassenger) {
                System.out.println(-1);
                return;
            }

            // 승객 태우러 가는 데 연료 소비
            fuel -= distToPassenger;

            Passenger p = passengers.get(index);

            // 승객 목적지까지 이동
            int distToDestination = moveTo(p.startX, p.startY, p.endX, p.endY);

            // 이동 불가 또는 연료 부족
            if (distToDestination == -1 || fuel < distToDestination) {
                System.out.println(-1);
                return;
            }

            // 연료 계산 (목적지까지 이동 후 2배 충전됨)
            fuel -= distToDestination;
            fuel += distToDestination * 2;

            // 택시 위치 갱신, 승객 처리
            taxiX = p.endX;
            taxiY = p.endY;
            p.picked = true;
        }

        // 최종 연료 출력
        System.out.println(fuel);
    }

    // 가장 가까운 승객을 찾는 함수
    static int[] findNearestPassenger() {
        visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(taxiX, taxiY, 0));
        visited[taxiX][taxiY] = true;

        ArrayList<Point> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            // 이미 최소 거리보다 크면 중단
            if (cur.dist > minDist) break;

            for (int i = 0; i < passengers.size(); i++) {
                Passenger p = passengers.get(i);

                // 아직 태우지 않은 승객이 현재 위치에 있다면
                if (!p.picked && p.startX == cur.x && p.startY == cur.y) {
                    if (cur.dist < minDist) {
                        candidates.clear();
                        minDist = cur.dist;
                    }
                    candidates.add(new Point(cur.x, cur.y, i)); // index 정보도 저장
                }
            }

            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, cur.dist + 1));
                    }
                }
            }
        }

        // 승객이 없다면
        if (candidates.isEmpty()) {
            return new int[]{-1, -1};
        }

        // 거리 같다면 행, 열 순으로 정렬
        candidates.sort((a, b) -> {
            if (passengers.get(a.dist).startX != passengers.get(b.dist).startX)
                return Integer.compare(passengers.get(a.dist).startX, passengers.get(b.dist).startX);
            return Integer.compare(passengers.get(a.dist).startY, passengers.get(b.dist).startY);
        });

        // 가장 우선순위 높은 승객 반환
        Point chosen = candidates.get(0);
        return new int[]{minDist, chosen.dist};
    }

    // 목적지까지의 최단 거리 구하기
    static int moveTo(int startX, int startY, int endX, int endY) {
        visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.x == endX && cur.y == endY) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, cur.dist + 1));
                    }
                }
            }
        }

        // 도착 불가능할 경우
        return -1;
    }
}
