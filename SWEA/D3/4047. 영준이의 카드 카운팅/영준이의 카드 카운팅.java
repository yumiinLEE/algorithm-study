import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스의 수
        int T = Integer.parseInt(br.readLine());
        
        // 각 테스트 케이스 처리
        for (int t = 1; t <= T; t++) {
            // 가지고 있는 카드 목록
            String cards = br.readLine();
            
            // 카드 덱 상태를 추적할 배열 (각 무늬별로 카드 개수를 추적)
            boolean[][] cardDeck = new boolean[4][13]; // S, D, H, C (4가지 무늬) x 13개의 카드
            
            // 중복 카드 여부 체크
            boolean error = false;
            
            for (int i = 0; i < cards.length(); i += 3) {
                String card = cards.substring(i, i + 3);
                
                // 카드의 무늬와 숫자 추출
                char suit = card.charAt(0);
                int num = Integer.parseInt(card.substring(1)) - 1; // 카드 숫자 (1~13) => 0~12로 변환
                
                // 무늬에 해당하는 인덱스 계산
                int suitIndex = -1;
                if (suit == 'S') suitIndex = 0;
                else if (suit == 'D') suitIndex = 1;
                else if (suit == 'H') suitIndex = 2;
                else if (suit == 'C') suitIndex = 3;
                
                // 이미 해당 카드가 있으면 오류
                if (cardDeck[suitIndex][num]) {
                    error = true;
                    break;
                }
                
                // 카드 존재 표시
                cardDeck[suitIndex][num] = true;
            }
            
            // 오류가 있으면 "ERROR" 출력
            if (error) {
                System.out.println("#" + t + " ERROR");
            } else {
                // 부족한 카드 수 계산
                int[] missingCards = new int[4]; // S, D, H, C 순으로 부족한 카드 개수
                
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 13; j++) {
                        if (!cardDeck[i][j]) {
                            missingCards[i]++;
                        }
                    }
                }
                
                // 결과 출력
                System.out.println("#" + t + " " + missingCards[0] + " " + missingCards[1] + " " + missingCards[2] + " " + missingCards[3]);
            }
        }
    }
}