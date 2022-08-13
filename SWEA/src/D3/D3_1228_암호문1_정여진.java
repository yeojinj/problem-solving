package D3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_1228_암호문1_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;		// 테스트 케이스의 수
		for (int tc = 1; tc <= T; tc++) {
			LinkedList<Integer> password = new LinkedList<>();
			int N = Integer.parseInt(br.readLine()); 	// 원본 암호문의 길이
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				password.add(Integer.parseInt(st.nextToken()));		// 원본 암호문
			}
			N = Integer.parseInt(br.readLine()); 		// 명령어의 개수
			st = new StringTokenizer(br.readLine());	// 명령어
			for(int i = 0; i < N; i++) {
				if (st.nextToken().equals("I")) {		// 명령어가 일치하면
					int x = Integer.parseInt(st.nextToken());	// 삽입할 위치
					int y = Integer.parseInt(st.nextToken());	// 삽입할 숫자 개수
					for(int j = 0; j < y; j++) {		// y개의 숫자를 삽입하기 위해서 y번 반복
						password.add(x++, Integer.parseInt(st.nextToken()));	// 현재 삽입한 숫자 뒤에 다음 숫자를 삽입해야 하므로 삽입 위치(x) 1씩 증가
					}
				}
			}
			
			// 출력
			System.out.print("#" + tc + " ");
			for(int i = 0; i < 10; i++) {		// 처음 10개 항만 출력
				System.out.print(password.get(i) + " ");
			}
			System.out.println();
		}
	}
}
