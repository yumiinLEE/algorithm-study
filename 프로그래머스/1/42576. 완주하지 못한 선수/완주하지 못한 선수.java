import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> cnt = new HashMap<>();
        
        for(String p : participant){
            if(cnt.containsKey(p)){
                cnt.put(p, cnt.get(p)+1);
            }
            else{
                cnt.put(p, 1);
            }
        }
            
         for(String s : completion){
             if(cnt.containsKey(s)){
                 cnt.put(s, cnt.get(s)-1);
             }
         }
        
        for(String key : cnt.keySet()){
            if(cnt.get(key) > 0){
                return key;
            }
        }
        return "";
    }
}