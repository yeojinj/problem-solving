package silver;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1158_요세푸스문제_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		
		int count = 1;		// K번째마다 제거하기 위한 카운터
		
		for(int n = 1; n <= N; n++) {
			q.add(n);		// 첫 번째 큐에 1~N 넣고 시작
		}
		
		sb.append("<");
		
		while(!q.isEmpty()) {
			if(count == K) {
				sb.append(q.poll() + ", ");		// 요세푸스 순열에 추가(출력)
				count = 1;		// 카운터 초기화
			} else {
				q.add(q.poll());	// 맨 앞에 있던 값을 맨 뒤로 이동시킴
				count++;		// 카운터 증가
			}
		}
		
		sb.delete(sb.length() - 2, sb.length());	// 출력 맨 뒤의 ", " 제거
		sb.append(">");
		bw.write(sb.toString() + '\n');
		bw.close();
		br.close();
	}
}
