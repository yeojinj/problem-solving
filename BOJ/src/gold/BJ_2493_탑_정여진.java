package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower {
	public int num;		// 탑 번호
	public int height;	// 탑 높이
	
	public Tower(int n, int h) {	// 생성자
		num = n; 
		height = h;
	}
}

public class BJ_2493_탑_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 탑의 수
		
		Tower towers[] = new Tower[N + 1];			// 탑 정보 저장할 공간
		towers[0] = new Tower(0, 100_000_001);		// 왼쪽 끝(0번째)에 벽이 있다고 가정함, 벽은 모든 탑보다 높음
		
		Stack<Tower> stack = new Stack<>();
		stack.push(towers[0]);
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			towers[i] = new Tower(i, Integer.parseInt(st.nextToken()));	// 탑 번호 저장, 탑 높이 입력 받기
			
			while (towers[i].height > stack.peek().height) {	// 현재 탑보다 낮은 앞의 탑들은 스택에서 제거
				stack.pop();
			}
			
			// 현재 탑보다 높은 앞의 탑을 발견하면
			System.out.print(stack.peek().num + " ");	// 탑 번호 출력
			stack.push(towers[i]);
		}
	}
}
