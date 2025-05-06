import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(reader.readLine());

        for (int t = 0; t < T; t++) {
            String command = reader.readLine(); // 수행할 함수
            int size = Integer.parseInt(reader.readLine()); // 배열 크기
            String array = reader.readLine(); // 배열 문자열

            Deque<Integer> deque = new ArrayDeque<>();

            // [1,2,3] -> "1,2,3" 으로 변환
            array = array.substring(1, array.length() - 1);
            if (size > 0) {
                String[] num = array.split(",");
                for (int i = 0; i < num.length; i++) {
                    deque.addLast(Integer.parseInt(num[i]));
                }
            }

            boolean reversed = false;
            boolean error = false;

            for (int i = 0; i < command.length(); i++) {
                char cmd = command.charAt(i);
                if (cmd == 'R') {
                    reversed = !reversed;
                } else if (cmd == 'D') {
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }
                    if (reversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }

            if (error) {
                System.out.println("error");
            } else {
                StringBuilder result = new StringBuilder();
                result.append('[');
                while (!deque.isEmpty()) {
                    if (reversed) {
                        result.append(deque.removeLast());
                    } else {
                        result.append(deque.removeFirst());
                    }
                    if (!deque.isEmpty()) {
                        result.append(',');
                    }
                }
                result.append(']');
                System.out.println(result.toString());
            }
        }
    }
}
