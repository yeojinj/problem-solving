package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_4012_요리사_정여진 {
	static int N;
	static int synergy[][];		// 식재료들 간의 시너지 값
	static int ans = Integer.MAX_VALUE;		// 두 음식 간의 맛의 차이의 최솟값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		// 총 테스트 케이스의 개수
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// 식재료의 수
			synergy = new int[N][N];					
			
			StringTokenizer st;
			for (int i = 0; i < synergy.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < synergy[i].length; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());	// 시너지 값 입력 받기
				}
			}
			
			comb(N, N/2);		// N개 중 N/2개 선택(조합)
			
			// 출력
			System.out.println("#" + tc + " " + ans);
			
			ans = Integer.MAX_VALUE;
		}
	}
	
	/** n개 중 k개 선택(조합) */
	static void comb(int n, int k) {
		for(int i = 0; i < 1<<n; i++) {
			int cnt = 0;	// 비트 1의 개수 count
			for(int j = 0; j < n; j++) {
				if((i & 1<<j) > 0) {
					cnt++;
				}
			}
			if(cnt == k) {	// n개 중 k개가 선택되었으면
				ArrayList<Integer> A = new ArrayList<>();	// A음식의 식재료 번호 저장
				ArrayList<Integer> B = new ArrayList<>();	// B음식의 식재료 번호 저장
				
				for(int j = 0; j < n; j++) {
					if((i & 1<<j) > 0) {
						A.add(j);		// A음식: i의 비트 1에 해당되는 식재료로 만들기
					} else {
						B.add(j);		// B음식: i의 비트 0에 해당되는 식재료로 만들기
					}
				}
				
				// 음식 맛 계산 -> 최솟값 구하기
				ans = Math.min(ans, getDiff(A, B));
			}
		}
	}
	
	/** 두 음식 간의 맛의 차이 return */
	static int getDiff(ArrayList<Integer> A, ArrayList<Integer> B) {
		int tasteA = 0;	// A음식의 맛 
		int tasteB = 0;	// B음식의 맛 
		
		taste = 0;
		perm(A, A.size(), 2, 0, 0);		// A에서 A.size()개 중 2개를 고르기
		tasteA = taste;	// A음식의 맛 저장
		
		taste = 0;
		perm(B, B.size(), 2, 0, 0);		// B에서 B.size()개 중 2개를 고르기
		tasteB = taste;	// B음식의 맛 저장
		
		return Math.abs(tasteA - tasteB);
	}
	
	static int permutation[] = new int[2];
	static int taste = 0;
	
	/** n개 중 k개의 순열 */
	static void perm(ArrayList<Integer> arr, int n, int k, int cnt, int j) {
		if(cnt == k) {
			taste += synergy[permutation[0]][permutation[1]];
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if((j & 1<<i) > 0) continue;
			permutation[cnt] = arr.get(i);		// 만들어지는 순열을 permutation에 저장
			perm(arr, n, k, cnt + 1, j | 1<<i);	
		}
	}
}
