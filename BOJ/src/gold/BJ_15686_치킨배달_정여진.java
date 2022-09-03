package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class location{
	int r;
	int c;
	public location(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class BJ_15686_치킨배달_정여진 {
	static ArrayList<location> houses = new ArrayList<>();
	static ArrayList<location> chickens = new ArrayList<>();
	
	static int chickenDist = 0;		// 도시의 치킨 거리
	static int minChickenDist = Integer.MAX_VALUE;	// 도시의 치킨 거리의 최솟값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x == 1) {
					houses.add(new location(i, j));
				} else if(x == 2) {
					chickens.add(new location(i, j));
				}
			}
		}
		
		for(int m = 1; m <= M; m++) {
			combination(chickens.size(), m);
		}
		
		System.out.println(minChickenDist);
	}
	
	/**
	 * 모든 치킨집 중 k개의 치킨집 고르기 (비트마스킹, 조합 응용)
	 * @param n (모든 치킨집의 수)
	 * @param k (조합 최대 선택 개수)
	 */
	static void combination(int n, int k) {		// n개 중 k개의 조합 고르기
		for(int i = 0; i < 1<<n; i++) {		
			int cnt = 0;
			for(int j = 0; j < n; j++) {	
				if((i & 1<<j) > 0) {
					cnt++;
				}
			}
				
			if(cnt == k) {	// 폐업시키지 않을 치킨집을 k개 고름
				calcDist(n, i);
			}
		}
	}
	
	/**
	 * combination에서 선택한 치킨집을 기준으로 도시의 치킨 거리 구하기
	 * @param n (모든 치킨집의 수)
	 * @param select (선택한 치킨집 조합(비트))
	 * @return 선택한 치킨집을 기준으로 구한 그 도시의 치킨 거리
	 */
	static void calcDist(int n, int select) {
		chickenDist = 0;
		for(int j = 0; j < houses.size(); j++) {		// 각 집의 치킨 거리 계산
			int minDist = Integer.MAX_VALUE;
			int hR = houses.get(j).r;
			int hC = houses.get(j).c;
			for(int i = 0; i < n; i++) {	
				if((select & 1<<i) > 0) {		// 조합으로 선택한 치킨집(i)을 기준으로 해당 집(j)의 치킨 거리 계산	
					int temp = Math.abs(hR - chickens.get(i).r) + Math.abs(hC - chickens.get(i).c);
					minDist = Math.min(minDist, temp);
				}
			}
			chickenDist += minDist;		// 모든 집의 치킨 거리의 합 -> 도시의 치킨 거리
		}
		minChickenDist = Math.min(minChickenDist, chickenDist);
	}
}