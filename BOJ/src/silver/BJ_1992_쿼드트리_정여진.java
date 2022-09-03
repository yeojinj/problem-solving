package silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_1992_쿼드트리_정여진 {
	
	static int arr[][];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = (int) str.charAt(j) - '0';
			}
		}
		
		quadTree(0, 0, N);
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	/**
	 * 4개의 구역으로 나누어 압축, 재귀 호출 
	 * r: 시작 행, c: 시작 열, n: 4개로 나눌 부분의 한 변의 길이
	 * */
	static void quadTree(int r, int c, int n) {
		boolean comp = true;	// 해당 구역을 하나로 압축 가능하면 true
		int temp = arr[r][c];
		Loop:
		for(int i = r; i < r + n; i++) {
			for(int j = c; j < c + n; j++) {
				if(temp != arr[i][j]) {
					comp = false; 
					break Loop;
				}
			}
		}
		if(comp == true) {
			sb.append(temp);
			return;
		}
		
		sb.append("(");
		
		// 1구역
		comp = true;	// 해당 구역을 하나로 압축 가능하면 true
		temp = arr[r][c];
		Loop1:
		for(int i = r; i < r + n/2; i++) {
			for(int j = c; j < c + n/2; j++) {
				if(temp != arr[i][j]) {
					comp = false; 
					break Loop1;
				}
			}
		}
		if(comp == true) sb.append(temp);
		else quadTree(r, c, n/2);
		
		// 2구역
		comp = true;
		temp = arr[r][c + n/2];
		Loop2:
		for(int i = r; i < r + n/2; i++) {
			for(int j = c + n/2; j < c + n; j++) {
				if(temp != arr[i][j]) {
					comp = false;  
					break Loop2;
				}
			}
		}
		if(comp == true) sb.append(temp);
		else quadTree(r, c + n/2, n/2);
		
		// 3구역
		comp = true;
		temp = arr[r + n/2][c];
		Loop3:
		for(int i = r + n/2; i < r + n; i++) {
			for(int j = c; j < c + n/2; j++) {
				if(temp != arr[i][j]) {
					comp = false; 
					break Loop3;
				}
			}
		}
		if(comp == true) sb.append(temp);
		else quadTree(r + n/2, c, n/2);
		
		// 4구역
		comp = true;
		temp = arr[r + n/2][c + n/2];
		Loop4:
		for(int i = r + n/2; i < r + n; i++) {
			for(int j = c + n/2; j < c + n; j++) {
				if(temp != arr[i][j]) {
					comp = false; 
					break Loop4;
				}
			}
		}
		if(comp == true) sb.append(temp);
		else quadTree(r + n/2, c + n/2, n/2);
		
		sb.append(")");
	}
}
