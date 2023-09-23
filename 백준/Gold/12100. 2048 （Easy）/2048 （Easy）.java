import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());		// 보드의 크기
		int[][] board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(board, 1);
		
		System.out.println(ans);
		
	}
	
	static void backTracking(int[][] board, int cnt) {
		if (cnt > 5) {		// 최대 5번 이동
			int max = getMax(board);
			ans = Math.max(max, ans);
			return;		
		}
		
		int[][] newBoard;
		newBoard = moveLeft(board);
		backTracking(newBoard, cnt + 1);
		newBoard = moveRight(board);
		backTracking(newBoard, cnt + 1);
		newBoard = moveUp(board);
		backTracking(newBoard, cnt + 1);
		newBoard = moveDown(board);
		backTracking(newBoard, cnt + 1);
		
	}
	
	static int[][] moveLeft(int[][] board) {
		int[][] newBoard = new int[N][N];
		boolean[][] combined = new boolean[N][N];
		int i, j, jj;
		for (i = 0; i < N; i++) {
			jj = 0;
			for (j = 0; j < N; j++) {
				if (board[i][j] != 0) {
					if (jj - 1 >= 0 && newBoard[i][jj - 1] == board[i][j] && !combined[i][jj - 1]) {	// 합치기 가능하면
						newBoard[i][jj - 1] *= 2;
						combined[i][jj - 1] = true;
					} else {													// 합치기 불가능하면
						newBoard[i][jj++] = board[i][j];
					}
				}
			}
			for (; jj < N; jj++) {
				newBoard[i][jj] = 0;	// 다 밀고 빈칸 0으로 채우기
			}
		}
		
		return newBoard;
	}
	
	static int[][] moveRight(int[][] board) {
		int[][] newBoard = new int[N][N];
		boolean[][] combined = new boolean[N][N];
		int i, j, jj;
		for (i = 0; i < N; i++) {
			jj = N - 1;
			for (j = N - 1; j >= 0; j--) {
				if (board[i][j] != 0) {
					if (jj + 1 < N && newBoard[i][jj + 1] == board[i][j] && !combined[i][jj + 1]) {	// 합치기 가능하면
						newBoard[i][jj + 1] *= 2;
						combined[i][jj + 1] = true;
					} else {												// 합치기 불가능하면
						newBoard[i][jj--] = board[i][j];
					}
				}
			}
			for (; jj >= 0; jj--) {
				newBoard[i][jj] = 0;	// 다 밀고 나머지는 빈칸 0으로 채우기
			}
		}
		
		return newBoard;
	}
	
	static int[][] moveUp(int[][] board) {
		int[][] newBoard = new int[N][N];
		boolean[][] combined = new boolean[N][N];
		int i, j, ii;
		for (j = 0; j < N; j++) {
			ii = 0;
			for (i = 0; i < N; i++) {
				if (board[i][j] != 0) {
					if (ii - 1 >= 0 && newBoard[ii - 1][j] == board[i][j] && !combined[ii - 1][j]) {	// 합치기 가능하면
						newBoard[ii - 1][j] *= 2;
						combined[ii - 1][j] = true;
					} else {													// 합치기 불가능하면
						newBoard[ii++][j] = board[i][j];
					}
				}
			}
			for (; ii < N; ii++) {
				newBoard[ii][j] = 0;	// 다 밀고 빈칸 0으로 채우기
			}
		}
		
		return newBoard;
	}
	
	static int[][] moveDown(int[][] board) {
		int[][] newBoard = new int[N][N];
		boolean[][] combined = new boolean[N][N];
		int i, j, ii;
		for (j = 0; j < N; j++) {
			ii = N - 1;
			for (i = N - 1; i >= 0; i--) {
				if (board[i][j] != 0) {
					if (ii + 1 < N && newBoard[ii + 1][j] == board[i][j] && !combined[ii + 1][j]) {	// 합치기 가능하면
						newBoard[ii + 1][j] *= 2;
						combined[ii + 1][j] = true;
					} else {												// 합치기 불가능하면
						newBoard[ii--][j] = board[i][j];
					}
				}
			}
			for (; ii >= 0; ii--) {
				newBoard[ii][j] = 0;	// 다 밀고 나머지는 빈칸 0으로 채우기
			}
		}
		
		return newBoard;
	}
	
	static int getMax(int[][] board) {
		int max = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, board[i][j]);
			}
		}
		return max;
	}
}