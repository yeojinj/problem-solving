package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2164_카드2_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Queue<Integer> cards = new LinkedList<>();
		
		// 초기 카드 상태 stack에 설정
		for (int i = 1; i <= N; i++) {
			cards.add(i);
		}
		
		while (cards.size() != 1) {		// 카드가 한 장 남을 때까지 반복
			// 제일 위에 있는 카드 바닥에 버림
			cards.poll();
			// 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮김
			int temp = cards.poll();
			cards.add(temp);
		}
		
		System.out.println(cards.peek());
	}

}
