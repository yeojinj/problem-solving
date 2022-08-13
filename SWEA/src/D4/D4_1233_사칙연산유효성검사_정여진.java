package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1233_사칙연산유효성검사_정여진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;		// 테스트 케이스 수
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 1;	// 계산이 가능하다면 1, 계산이 불가능할 경우 0
			
			int i = 0;
			for(; i < N; i++) {
				String[] str = br.readLine().split(" ");
				if(str.length == 4) {	// 입력이 4개인데 2번째 문자가 연산자가 아니면 break
					if(!(str[1].equals("+") || str[1].equals("-") || str[1].equals("*") || str[1].equals("/"))) {
						ans = 0;
						break;
					}
				} else if(str.length == 2) {	// 입력이 2개인데 2번째 문자가 연산자이면 break
					if(str[1].equals("+") || str[1].equals("-") || str[1].equals("*") || str[1].equals("/")) {
						ans = 0;
						break;
					}
				} else if (str.length == 3) {	// 입력이 3개이면 break
					ans = 0;
					break;
				}
			}
			i++;		// break 후 그 다음 줄로 넘어감
			
			for(; i < N; i++) {
				br.readLine();		// break 후 나머지 입력 받기
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
