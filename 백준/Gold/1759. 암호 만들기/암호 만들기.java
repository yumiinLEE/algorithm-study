import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] alpha;
    static char[] password;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        alpha = new char[C];
        password = new char[L];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
        
        // 알파벳 정렬
        Arrays.sort(alpha);
        
        dfs(0, 0);
    }

    static void dfs(int start, int cnt) {
        if (cnt == L) {
            // 암호가 완성되었을 때 유효성 검사
            if (isValid(password)) {
                System.out.println(new String(password));
            }
            return;
        }
        
        // 조합
        for (int i = start; i < C; i++) {
            password[cnt] = alpha[i];
            dfs(i+1, cnt+1);
        }
    }

    // 암호의 유효성 검사 함수
    static boolean isValid(char[] pw) {
        int jcnt = 0;
        int mcnt = 0;
        
        // 주어진 암호에서 모음과 자음 개수를 셈
        for (char c : pw) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                mcnt++;
            } else {
                jcnt++;
            }
        }
        
        return mcnt >= 1 && jcnt >= 2;
    }
}