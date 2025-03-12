
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] line = br.readLine().split(" ");
        
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        
     // HashSet을 사용하여 집합 S에 포함된 문자열 저장
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
        	String s = br.readLine();
			set.add(s);
		}
        
        //집합 S에 포함된 문자열 개수를 계산
        int cnt = 0;
        for (int i = 0; i < M; i++) {
        	String s = br.readLine();
        	if(set.contains(s)) {
        		cnt++;
        	}
		}
    System.out.print(cnt);
    }
}