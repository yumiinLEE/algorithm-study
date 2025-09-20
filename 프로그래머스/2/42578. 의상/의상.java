import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap();
        
        for(String[] c : clothes){
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }
        
        int way = 1;
        for(int k : map.values()){
            way *= (k+1);
        }
        
        return way-1;
    }
}