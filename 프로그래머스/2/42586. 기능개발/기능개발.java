import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] days = new int[progresses.length];
        
        for(int i = 0; i < progresses.length; i++){
            days[i] = (int) Math.ceil((100.0-progresses[i])/speeds[i]);
        }

        List<Integer> answer = new ArrayList<>();
        
        int cur = days[0];
        int cnt = 0;
        for(int i = 0; i < progresses.length; i++){
            if(days[i]<=cur){
                cnt++;
            }
            else if(days[i]>cur){
                answer.add(cnt);
                cnt=1;
                cur = days[i];
            }
        }
        answer.add(cnt);
        
        int[] ans = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            ans[i] = answer.get(i);
        }
        
        return ans;
    }
}