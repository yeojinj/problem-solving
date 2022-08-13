package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_12891_DNA비밀번호_정여진 {
	static String str;					// DNA 문자열
	static HashMap<Character, Integer> map = new HashMap<>();	// 부분문자열에 포함된  A, C, G, T의 개수 count하는 HashMap
																// Key: DNA 문자(A, C, G, T), Value: 각 문자의 개수
	static int A, C, G, T;		// 부분문자열에 포함되어야 할 A, C, G, T의 최소 개수

	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());	// DNA 문자열 길이
		int P = Integer.parseInt(st.nextToken());	// 비밀번호로 사용할 부분문자열의 길이
		
		str = br.readLine();					// DNA 문자열
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 부분문자열의 A, C, G, T counter 초기화
		map.put('A', 0);	
		map.put('C', 0);
		map.put('G', 0);
		map.put('T', 0);
		
		int start = 0;		// 부분문자열 시작 index
		int end = P;		// 부분문자열 끝 index + 1
		int ans = 0;		// 비밀번호 종류의 수
		
		for(int i = start; i < end; i++) {
			map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
		}
		
		while(true) {
			if(check()) {
				ans++;
			}
			if(end >= S) {
				break;
			}
			moveStart(start);	
			start++;			// 부분문자열 시작 포인터 이동
			end++;				// 부분문자열 끝 포인터 이동
			moveEnd(end);		
		}
		
		// 출력
		System.out.println(ans);
	}
	
	/** 비밀번호로 사용할 수 있는지(부분문자열에 포함되어야 할 A, C, G, T의 최소 개수를 충족하는지) 체크 */
	public static boolean check() {
		if(map.get('A') >= A && map.get('C') >= C && map.get('G') >= G && map.get('T') >= T) {
			return true;
		} else {
			return false;
		}
	}
	
	/** start 포인터 이동 시 counter 값 변경 */
	public static void moveStart(int s) {
		map.put(str.charAt(s), map.get(str.charAt(s)) - 1);
	}
	
	/** end 포인터 이동 시 counter 값 변경 */
	public static void moveEnd(int e) {
		map.put(str.charAt(e - 1), map.get(str.charAt(e - 1)) + 1);
	}
}
