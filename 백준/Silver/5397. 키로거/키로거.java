import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		MyStack front = new MyStack(1000001);
		MyStack back = new MyStack(1000001);
		for(int testCase = 0 ; testCase< T; testCase++) {
			String commend = reader.readLine();
			for(int i=0, size = commend.length(); i < size; i++) {
				switch(commend.charAt(i)) {
				case '<':back.push(front.pop());
					break;
				case '>':front.push(back.pop());
					break;
				case '-':front.pop();
					break;
				default :front.push(commend.charAt(i));
					break;
				}
			}
			
			System.out.print(front.toString());
			System.out.println(back.toReverseString());
			front.clear();
			back.clear();
		}
		
	}
	
	static class MyStack{
		char[] stack;
		int size;
		public MyStack(int size) {
			stack = new char[size];
			this.size = 0;
		}
		
		public void push(char word) {
			if(word != ' ') {
				stack[size++] = word;
			}
		}
		
		public char pop() {
			if(size == 0) {
				return ' ';
			}else {
				return stack[--size];
			}
		}
		
		public String toString() {
			return String.valueOf(stack, 0, size);
		}
		
		public String toReverseString() {
			char[] reverse = new char[size];
			int lastIdx = size-1;
			for(int i = 0; i < size; i++) {
				reverse[i] = stack[lastIdx-i];
			}
			return String.valueOf(reverse);
		}
		
		public void clear() {
			size = 0;
		}
		
	}
}
