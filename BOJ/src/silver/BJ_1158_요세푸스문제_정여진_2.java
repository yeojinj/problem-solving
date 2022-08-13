package silver;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1158_요세푸스문제_정여진_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> A = new LinkedList<>();
		Queue<Integer> B = new LinkedList<>();
		
		int count = 1;	// K번째마다 제거하기 위한 카운터
		
		// 첫 번째 큐에 1~N 넣고 시작
		for(int n = 1; n <= N; n++) {
			A.add(n);
		}
		
		boolean end = false;
		
		sb.append("<");
		
		while(true) {
			// 큐 A -> B
			while(!A.isEmpty()) {
				if(count % K == 0) {
					sb.append(A.poll() + ", ");
					count = 1;
				} else {
					B.add(A.poll());
					count++;
				}
			}
			if(B.isEmpty()) {
				break;
			}
			// 큐 B -> A
			while(!B.isEmpty()) {
				if(count % K == 0) {
					sb.append(B.poll() + ", ");
					count = 1;
				} else {
					A.add(B.poll());
					count++;
				}
			}
			if(A.isEmpty()) {
				break;
			}
		}
		
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		bw.write(sb.toString() + '\n');
		bw.close();
		br.close();
	}
}
