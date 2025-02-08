import java.io.*;

public class Solution {
    static final int SIZE = 100;
    static char[][] board;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) { // 테스트 케이스
            int testCase = Integer.parseInt(br.readLine());
            board = new char[SIZE][SIZE];

            // 글자판 입력
            for (int i = 0; i < SIZE; i++) {
                board[i] = br.readLine().toCharArray();
            }

            int maxLength = 1;

            // 가로 방향 탐색
            for (int i = 0; i < SIZE; i++) {
                maxLength = Math.max(maxLength, findMaxPalindrome(board[i]));
            }
            
            // 세로 방향 탐색
            for (int j = 0; j < SIZE; j++) {
                char[] column = new char[SIZE];
                for (int i = 0; i < SIZE; i++) {
                    column[i] = board[i][j];
                }
                maxLength = Math.max(maxLength, findMaxPalindrome(column));
            }

            System.out.println("#" + testCase + " " + maxLength);
        }

        br.close();
    }

    // 가장 긴 회문의 길이를 찾는 함수
    private static int findMaxPalindrome(char[] arr) {
        int maxLen = 1;

        for (int start = 0; start < SIZE; start++) {
            for (int end = start; end < SIZE; end++) {
                if (isPalindrome(arr, start, end)) {
                    maxLen = Math.max(maxLen, end - start + 1);
                }
            }
        }

        return maxLen;
    }

    // 주어진 범위가 회문인지 확인
    private static boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) return false;
            left++;
            right--;
        }
        return true;
    }
}