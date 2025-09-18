import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        List<Integer> lst = new ArrayList<>();
        
        int pre = arr[0];
        lst.add(pre);
        
        for(int i = 1; i < arr.length; i++){
            if(pre != arr[i]){
                lst.add(arr[i]);
                pre = arr[i];
            }
        }
        
        int[] answer = new int[lst.size()];
        
        for(int i = 0; i < lst.size(); i++){
            answer[i] = lst.get(i);
        }
        
        return answer;
    }
}