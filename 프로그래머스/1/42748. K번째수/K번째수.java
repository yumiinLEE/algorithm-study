import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        
        for(int tc = 0; tc < commands.length; tc++){
            int i = commands[tc][0]-1;
            int j = commands[tc][1]-1;
            int k = commands[tc][2]-1;
            
            int[] lst = new int[j-i+1];
            
            for(int t = 0; t <j-i+1 ; t++){
                lst[t] = array[i+t];
            }
            
            Arrays.sort(lst);
            answer[tc] = lst[k];
        }
        
        return answer;
    }
}