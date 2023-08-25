import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        int count = 1;  // 현재 스택에 push 할 숫자
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (!stack.isEmpty()) {
                if (stack.peek() == x) {
                    sb.append('-').append('\n');
                    stack.pop();
                } else if (stack.peek() < x) {
                    while (count <= x) {
                        stack.push(count++);
                        sb.append('+').append('\n');
                    }
                    if (stack.peek() == x) {
                        sb.append('-').append('\n');
                        stack.pop();
                    } else {
                        sb.delete(0, sb.length());
                        sb.append("NO\n");
                        break;
                    }
                } else {
                    sb.delete(0, sb.length());
                    sb.append("NO\n");
                    break;
                }
            } else {
                while (count <= x) {
                    stack.push(count++);
                    sb.append('+').append('\n');
                }
                if (stack.peek() == x) {
                    sb.append('-').append('\n');
                    stack.pop();
                } else {
                    sb.delete(0, sb.length());
                    sb.append("NO\n");
                    break;
                }
            }
        }

        i++;
        for (; i < N; i++) {
            br.readLine();
        }

        System.out.println(sb);

    }

}