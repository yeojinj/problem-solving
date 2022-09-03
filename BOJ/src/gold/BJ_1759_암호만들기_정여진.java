package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1759_암호만들기_정여진 {
	static int L, C;
	static char input[];
	static char password[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());	// 암호는 서로 다른 L개의 알파벳 소문자들로 구성
		password = new char[L];
		C = Integer.parseInt(st.nextToken());	// 암호로 사용했을 법한 문자의 종류
		input = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(input);
		
		comb(0, 0);
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == L) {
			int vowelCnt = 0;
			for(int i = 0; i < L; i++) {
				if(isVowel(password[i])) vowelCnt++;
			}
			if(vowelCnt >= 1 && (L - vowelCnt) >= 2) {
				for(int i = 0; i < L; i++) {
					System.out.print(password[i]);
				}
				System.out.println();
				return;
			} else {
				return;
			}
		}
		
		for(int i = start; i < C; i++) {
			password[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}
	
	private static boolean isVowel(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
		else return false;
	}
}
