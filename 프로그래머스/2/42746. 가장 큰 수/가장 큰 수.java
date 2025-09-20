import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] s = new String[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            s[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(s, (a,b) -> (b+a).compareTo(a+b));
        
        StringBuilder sb = new StringBuilder();
        
        if (s[0].equals("0")) return "0";
        
        for(String x : s){
            sb.append(x);
        }
        return sb.toString();
    }
}