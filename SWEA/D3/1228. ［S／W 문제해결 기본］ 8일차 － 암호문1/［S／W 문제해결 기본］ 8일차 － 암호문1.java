import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int t = 1; t <= 10; t++) { // 10개의 테스트 케이스
            int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
            LinkedList<Integer> list = new LinkedList<>();
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            
            int commandCount = Integer.parseInt(br.readLine()); // 명령어 개수
            st = new StringTokenizer(br.readLine());
            
            while (st.hasMoreTokens()) {
                String command = st.nextToken(); // 명령어 타입 ("I")
                if (command.equals("I")) {
                    int x = Integer.parseInt(st.nextToken()); // 삽입 위치
                    int y = Integer.parseInt(st.nextToken()); // 삽입할 개수
                    
                    for (int j = 0; j < y; j++) {
                        int s = Integer.parseInt(st.nextToken()); // 삽입할 숫자
                        if (x + j < list.size()) {
                            list.add(x + j, s);
                        } else {
                            list.add(s);
                        }
                    }
                }
            }
            
            // 결과 출력 (처음 10개 숫자)
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < 10 && i < list.size(); i++) {
                sb.append(list.get(i)).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
        
        br.close();
    }
}