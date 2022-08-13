package D3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임_정여진 {
	
	static int size = 9;
	static int arr1[] = new int[size];	// 규영이의 카드
	static int arr2[] = new int[size];	// 인영이의 카드
	static boolean visited[] = new boolean[size];	// 순열 구할 때 필요한 배열
	static int[] output = new int[size];	// 순열 결과 저장하는 배열

	static int winCount = 0;	// 규영이가 이기는 수
	static int loseCount = 0;	// 규영이가 지는 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int check[] = new int[size*2 + 1];	// 1: 규영 / 0: 인영
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < size; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
				check[arr1[i]] = 1;
			}
			
			// 규영이가 받은 카드를 제외한 나머지를 인영이의 카드에 저장
			int count = 0;
			for(int i = 1; i <= size*2; i++) {
				if(check[i] == 0) {
					arr2[count++] = i;
				}
			}
			
			winCount = 0;
			loseCount = 0;
			// 인영이의 카드로 가능한 순열을 모두 구해서 규영이의 카드와 비교
			permutation(visited, 0);

			sb.append("#").append(tc).append(" ").append(winCount).append(" ").append(loseCount).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
	}
	
	static void permutation(boolean[] visited, int depth) {
		if(depth == size) {
			int score1 = 0;	// 규영이의 총점
			int score2 = 0;	// 인영이의 총점
			for (int i = 0; i < output.length; i++) {
				if(arr1[i] > output[i]) {	// 규영이 카드의 숫자가 더 클 경우
					score1 += arr1[i] + output[i];
				} else {					// 인영이 카드의 숫자가 더 클 경우
					score2 += arr1[i] + output[i];
				}
			}
			if(score1 > score2) winCount++;
			else loseCount++;
			return;
		}
		
		for(int i = 0; i < size; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				output[depth] = arr2[i];
				permutation(visited, depth + 1);
				visited[i] = false;
			}
		}
	}
}