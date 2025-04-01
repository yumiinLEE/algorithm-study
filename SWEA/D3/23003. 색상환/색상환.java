import java.io.*;
import java.util.*;

public class Solution {
	static String[] COLORS = {"red", "orange", "yellow", "green", "blue", "purple"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String color1 = st.nextToken();
            String color2 = st.nextToken();
            System.out.println(checkRelationship(color1, color2));
        }
    }

    public static String checkRelationship(String color1, String color2) {
        if (color1.equals(color2)) return "E";

        int index1 = getIndex(color1);
        int index2 = getIndex(color2);
        int distance = Math.abs(index1 - index2);

        if (distance == 1 || distance == COLORS.length - 1) return "A";
        if (distance == COLORS.length / 2) return "C";

        return "X";
    }

    public static int getIndex(String color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
            	return i;
            }
        }
        return -1;
    }
}