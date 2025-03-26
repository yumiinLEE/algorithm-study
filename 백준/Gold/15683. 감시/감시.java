import java.io.*;
import java.util.*;

class CCTV {
    int x;
    int y;
    int type;
    
    CCTV(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

public class Main {
    static int N, M;
    static int min = Integer.MAX_VALUE;
    static int[][] office;
    static List<CCTV> cctvs = new ArrayList<>();
    static int[][][] directions = {
            {},
            {{0, 1}}, {{0, 1}, {0, -1}}, {{-1, 0}, {0, 1}}, {{-1, 0}, {0, 1}, {1, 0}},
            {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, office[i][j]));
                }
            }
        }
        
        backtrack(0);
        System.out.println(min);
    }
    
    static void backtrack(int idx) {
        if (idx == cctvs.size()) {
            min = Math.min(min, countBlindSpot());
            return;
        }
        
        CCTV cctv = cctvs.get(idx);
        int type = cctv.type;
        for (int rot = 0; rot < (type == 2 ? 2 : type == 5 ? 1 : 4); rot++) {
            int[][] temp = copyArray(office);
            monitor(cctv.x, cctv.y, type, rot);
            backtrack(idx + 1);
            office = temp;
        }
    }
    
    static void monitor(int x, int y, int type, int rotation) {
        for (int[] dir : directions[type]) {
            int dx = rotate(dir[0], dir[1], rotation)[0];
            int dy = rotate(dir[0], dir[1], rotation)[1];
            int nx = x, ny = y;
            while (isValid(nx + dx, ny + dy) && office[nx + dx][ny + dy] != 6) {
                nx += dx;
                ny += dy;
                if (office[nx][ny] == 0) office[nx][ny] = -1;
            }
        }
    }
    
    static int[] rotate(int dx, int dy, int rot) {
        for (int i = 0; i < rot; i++) {
            int temp = dx;
            dx = dy;
            dy = -temp;
        }
        return new int[]{dx, dy};
    }
    
    static int countBlindSpot() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i][j] == 0) count++;
            }
        }
        return count;
    }
    
    static int[][] copyArray(int[][] arr) {
        int[][] newArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, M);
        }
        return newArr;
    }
    
    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}