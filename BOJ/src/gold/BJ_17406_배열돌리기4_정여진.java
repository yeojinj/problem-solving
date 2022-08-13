package gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4_정여진 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static int N;
	static int M;
	static int K;
	
	static int[][] originArr;
	static int[][] newArr;
	
	static boolean[] visited;
	static int[] numbers;
	static ArrayList<int[]> permutations;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		originArr = new int[N + 1][M + 1];
		newArr = new int[N + 1][M + 1];
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= M;c++) {
				originArr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] operation = new int[K][3];
		
		for(int r = 0; r < K; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 3; c++) {
				operation[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[K];
		numbers = new int[K];
		permutations = new ArrayList<>(100);
		permutation(0);
		
		int ans = Integer.MAX_VALUE;
		for(int[] permutation : permutations) {
			int calc = 0;
			
			for(int r = 0; r < N + 1; r++) {
				for(int c = 0; c < M + 1; c++) {
					newArr[r][c] = originArr[r][c];
				}
			}
			
			for(int order : permutation) {
				rotate(operation[order][0], operation[order][1], operation[order][2]);
			}
			
			calc = getMinSum();
			if(calc < ans) ans = calc;
		}
		System.out.println(ans);
	}
	
	static void rotate(int r, int c, int s) {
		if(s > 0) {
			int startR = r-s;
			int startC = c-s;
			int newR, newC;
			int dir = 0;
			int temp = newArr[startR][startC];
			int newTemp;
			while(dir < 4) {
				newR = startR + dr[dir];
				newC = startC + dc[dir];
				if(newR < r-s || newR > r+s || newC < c-s || newC > c+s) {
					dir++;
				} else {
					newTemp = newArr[newR][newC];
					newArr[newR][newC] = temp;
					temp = newTemp;
					startR = newR;
					startC = newC;
				}
			}
			rotate(r, c, s-1);
		}
		
	}
	
	static void permutation(int depth) {
		if(depth == K) {
			permutations.add(numbers.clone());
			return;
		}
		for(int i = 0; i < K; i++) {
			if(!visited[i]) {
				numbers[depth] = i;
				visited[i] = true;
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}

	static int getMinSum() {
		int sum;
		int minSum = Integer.MAX_VALUE;
		for(int r = 1; r <= N; r++) {
			sum = 0;
			for(int c = 1; c <= M; c++) {
				sum += newArr[r][c];
			}
			if(sum < minSum) minSum = sum;
		}
		return minSum;
	}

}