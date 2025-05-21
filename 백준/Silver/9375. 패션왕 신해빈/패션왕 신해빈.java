import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
		int T = Integer.parseInt(br.readLine());
    	
		for (int t = 0; t < T; t++) {
			// 의상 개수
			int N = Integer.parseInt(br.readLine());
			
			// 의상의 종류를 저장할 HashMap 생성
            // key: 의상 종류, value: 해당 의상 종류에 속하는 의상 수
			HashMap<String, Integer> map = new HashMap<>();
			
			// 의상 종류별로 의상의 수를 카운트
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split(" ");
				// `map.getOrDefault(line[1], 0)`은 line[1] 종류가 없으면 0을 반환하고, 있으면 그 값을 반환
                // 그 후 +1을 해서 해당 종류의 의상 개수를 증가
				map.put(line[1], map.getOrDefault(line[1], 0)+1);
				}
			
			// 가능한 의상 조합의 수를 계산
			int sum = 1;
			for (int cnt : map.values()) {
				// +1은 해당 종류의 의상을 "안 입는 경우"도 하나의 선택으로 포함
				sum *= (cnt+1);
			}
			// 알몸인 경우는 제외
			System.out.println(sum-1);
		}	
    }
}