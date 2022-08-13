package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D3_1225_암호생성기_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tc = Integer.parseInt(st.nextToken());
			
			Queue<Integer> pw = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				pw.add(Integer.parseInt(st.nextToken()));
			}
			
			int count = 1;
			while (true) {
				int temp = pw.poll();		// 앞 숫자 가져옴
				temp -= count;				// count만큼 감소 
				
				if (temp <= 0) {			// 뒤로 이동할 숫자가 0보다 작아지거나 0일 경우
					pw.add(0);				// 0으로 저장
					break;					// 암호 도출
				}
				pw.add(temp);				// 숫자 뒤로 이동
				count++;
				
				if (count > 5) {	// 한 사이클 종료
					count = 1;
				}
			}
			
			// 출력
			System.out.print("#" + test_case + " ");
			for (int i = 0; i < 8; i++) {
				System.out.print(pw.poll() + " ");
			}
			System.out.println();
		}
	}
}
