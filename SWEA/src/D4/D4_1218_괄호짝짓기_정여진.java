package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class D4_1218_괄호짝짓기_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			Stack <Character> stack = new Stack();
			int ans = 0;		// 1: 유효함, 0: 유효하지 않음
			
			for (int i= 0; i < N; i++) {
				char c = str.charAt(i);
				
				if (c == ')') {
					if (!stack.isEmpty() && stack.peek() == '(') {	// 짝이 맞으면 pop
						stack.pop();
					} else {			// 닫는 괄호 앞에 아무것도 없거나 다른 여는 괄호가 있을 경우
						ans = 0;
						break;
					}
				} else if (c == ']') {
					if (!stack.isEmpty() && stack.peek() == '[') {	// 짝이 맞으면 pop
						stack.pop();
					} else {			// 닫는 괄호 앞에 아무것도 없거나 다른 여는 괄호가 있을 경우
						ans = 0;
						break;
					}
				} else if (c == '}') {
					if (!stack.isEmpty() && stack.peek() == '{') {	// 짝이 맞으면 pop
						stack.pop();
					} else {			// 닫는 괄호 앞에 아무것도 없거나 다른 여는 괄호가 있을 경우
						ans = 0;
						break;
					}
				} else if (c == '>') {
					if (!stack.isEmpty() && stack.peek() == '<') {	// 짝이 맞으면 pop
						stack.pop();
					} else {			// 닫는 괄호 앞에 아무것도 없거나 다른 여는 괄호가 있을 경우
						ans = 0;
						break;
					}
				} else {
					stack.push(c);		// 여는 괄호일 경우 push
				}
				
			}
			
			if (stack.isEmpty()) {		// stack이 전부 비워졌을 경우 유효함
				ans = 1;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
