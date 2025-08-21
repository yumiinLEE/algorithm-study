import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int max = Integer.MIN_VALUE;

        for (int i = 0; i <T; i++){
            st = new StringTokenizer(br.readLine());

            max = 0;
            for (int j = 0; j < 10; j++){
                int num = Integer.parseInt(st.nextToken());

                if (num > max) {
                    max = num;
                }
            }

            System.out.println("#" + (i+1) + " " +  max);
        }
    }
}