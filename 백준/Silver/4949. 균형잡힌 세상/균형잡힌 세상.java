import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            }

            Stack<Character> stack = new Stack<>();

            boolean isBalanced = true;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                switch (c) {
                    case '(':
                        stack.push('(');
                        break;
                    case ')':
                        if (stack.isEmpty()) {
                            isBalanced = false;
                            break;
                        }
                        c = stack.pop();
                        if (c != '(') {
                            isBalanced = false;
                        }
                        break;
                    case '[':
                        stack.push('[');
                        break;
                    case ']':
                        if (stack.isEmpty()) {
                            isBalanced = false;
                            break;
                        }
                        c = stack.pop();
                        if (c != '[') {
                            isBalanced = false;
                        }
                        break;
                }
                if (!isBalanced) break;
            }

            if (!stack.isEmpty()) isBalanced = false;

            if (isBalanced) System.out.println("yes");
            else System.out.println("no");
        }

    }


}