import java.io.*;
import java.util.*;

public class Main {
	static class Lecture implements Comparable<Lecture>{
		int payment;
		int day;
		
	    public Lecture(int payment, int day) {
	        this.payment = payment;
	        this.day = day;
	    }

		@Override
		public int compareTo(Lecture o) {
			return o.payment-this.payment;  // 강연료를 기준으로 내림차순 정렬
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        int n = Integer.parseInt(br.readLine());
        
        List<Lecture> lectures = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 강연료
            int d = Integer.parseInt(st.nextToken()); // 날짜
            lectures.add(new Lecture(p, d));
        }
        
        Collections.sort(lectures);
        
        // 강연을 할 수 있는 가장 늦은 날짜를 찾기
        int maxDate = 0;
        for (Lecture lecture : lectures) {
            maxDate = Math.max(maxDate, lecture.day);
        }  
        
        // 강연한 날짜 방문 여부 배열 
        boolean[] visited = new boolean[maxDate + 1];
        
        // 총 강연료
        int totalPay = 0;

        // 강연 리스트를 순회하면서 가장 높은 강연료부터 할 수 있는 날짜에 강연을 배정
        for (Lecture lecture : lectures) {
            int p = lecture.payment;
            int d = lecture.day;

            // 가능한 날짜 중에서 가장 늦은 날짜를 찾음
            while (d > 0 && visited[d]) {
                d--;  // 이미 강연이 있으면 그 전날로 시도
            }

            // 강연이 가능한 날짜를 찾으면 강연을 하고, 그 날짜는 방문 처리
            if (d > 0) {
                visited[d] = true;
                totalPay += p;
            }
        }

        System.out.println(totalPay);
	}
}