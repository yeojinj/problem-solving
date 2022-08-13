package silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16935_배열돌리기3_정여진 {
	static int arr[][];
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		String[] op = br.readLine().split(" ");
		for(int o = 0; o < R; o++) {
			switch(op[o]) {
			case "1": // 상하 반전
				upDown();
				break;
			case "2": // 좌우 반전
				leftRight();
				break;
			case "3": // 오른쪽 90도 회전
				right90();
				break;
			case "4": // 왼쪽 90도 회전
				left90();
				break;
			case "5": //4개의 부분 배열로 나눈 후 시계방향 회전
				clockWise();
				break;
			case "6": //4개의 부분 배열로 나눈 후 반시계방향 회전
				antiClockWise();
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void upDown() {
		int half = N / 2;
		for(int i = 0; i < half; i++) {
			for(int j = 0; j < M; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[N-1-i][j];
				arr[N-1-i][j] = temp;
			}
		}
	}
	static void leftRight() {
		int half = M / 2;
		for(int i=0; i < N; i++) {
			for(int j = 0; j < half; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][M-1-j];
				arr[i][M-1-j] = temp;
			}
		}
	}
	static void right90() {
		int[][] newArr = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				newArr[i][j] = arr[N-1-j][i];
			}
		}
		
		int temp = M;
		M = N;
		N = temp;
		arr = newArr;
	}
	static void left90() {
		int[][] newArr = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				newArr[i][j] = arr[j][M-1-i];
			}
		}
		int temp = M;
		M = N;
		N = temp;
		arr = newArr;
	}
	static void clockWise() {
		int mHalf = M/2;
		int nHalf = N/2;
		
		// 좌상, 우상, 좌하, 우하 네 구역 (arr1, arr2, arr3, arr4)
		int[][] arr1 = new int[nHalf][mHalf];
		int[][] arr2 = new int[nHalf][mHalf];
		int[][] arr3 = new int[nHalf][mHalf];
		int[][] arr4 = new int[nHalf][mHalf];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i < nHalf && j < mHalf)
					arr1[i][j] = arr[i][j];
				else if(i < nHalf && j >= mHalf)
					arr2[i][j-mHalf] = arr[i][j];
				else if(i >= nHalf && j < mHalf)
					arr3[i-nHalf][j] = arr[i][j];
				else
					arr4[i-nHalf][j-mHalf] = arr[i][j];
			}
		}
		
		// 네 구역 시계방향으로 합치기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i < nHalf && j < mHalf)
					arr[i][j] = arr3[i][j];
				else if(i < nHalf && j >= mHalf)
					arr[i][j] = arr1[i][j-mHalf];
				else if(i >= nHalf && j < mHalf)
					arr[i][j] = arr4[i-nHalf][j];
				else
					arr[i][j] = arr2[i-nHalf][j-mHalf];
			}
		}
	}
	static void antiClockWise() {
		int mHalf = M/2;
		int nHalf = N/2;
		
		// 좌상, 우상, 좌하, 우하 네 구역 (arr1, arr2, arr3, arr4)
		int[][] arr1 = new int[nHalf][mHalf];
		int[][] arr2 = new int[nHalf][mHalf];
		int[][] arr3 = new int[nHalf][mHalf];
		int[][] arr4 = new int[nHalf][mHalf];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i < nHalf && j < mHalf)
					arr1[i][j] = arr[i][j];
				else if(i < nHalf && j >= mHalf)
					arr2[i][j-mHalf] = arr[i][j];
				else if(i >= nHalf && j < mHalf)
					arr3[i-nHalf][j] = arr[i][j];
				else
					arr4[i-nHalf][j-mHalf] = arr[i][j];
			}
		}
		
		// 네 구역 반시계방향으로 합치기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i < nHalf && j < mHalf)
					arr[i][j] = arr2[i][j];
				else if(i < nHalf && j >= mHalf)
					arr[i][j] = arr4[i][j-mHalf];
				else if(i >= nHalf && j < mHalf)
					arr[i][j] = arr1[i-nHalf][j];
				else
					arr[i][j] = arr3[i-nHalf][j-mHalf];
			}
		}
	}
}
