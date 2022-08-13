package gold;

// 행 별로 합 구하는 부분에서 오류난 거 같음!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4_정여진_실패 {
	
	static int di[] = {-1, 1, 0, 0};	// 상 하 좌 우
	static int dj[] = {0, 0, -1, 1};
	static int N;		// 배열 크기 NxM
	static int M;
	static int K;		// 회전 연산의 개수
	
	static int originArr[][];	// 맨 처음 입력 값 save
	static int arr[][];		// 회전하기 전 배열
	static int newArr[][];	// 회전한 후 배열
	
	static int operations[][];
	static int opNum[];					// 회전 연산 사용 순서(순열)
	static boolean visited[];			// 순열 구할 때 필요한 배열
	static int opNumPerm[];				// opNum[]의 모든 순열 결과 저장
	
	static int ans;		// 배열의 값의 최솟값 (최종 출력값)
	
	static int count = 0; 		//=================================

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 배열 크기 NxM 입력 받기
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());	// 회전 연산의 개수 입력 받기
		
		originArr = new int[N + 1][M + 1];
		arr = new int[N + 1][M + 1];
		newArr = new int[N + 1][M + 1];
		
		// 배열 A 입력 받기
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k = 0; k < arr.length; k++) {	// originArr <- arr 복사 (원본 유지)
			System.arraycopy(arr[k], 0, originArr[k], 0, arr[k].length);
		}
		
//		System.out.println("=========원래 배열============");
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= M; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("============================");
		
		operations = new int[K][3];
		// 회전 연산의 정보 입력 받기(K개)
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				operations[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 회전 연산 사용 순서를 정할 배열 초기값 세팅 (0 ~ K-1)
		opNum = new int[K];
		for(int i = 0; i < K; i++) {
			opNum[i] = i;
		}
		opNumPerm = new int[K];
		visited = new boolean[K];	// 순열 구할 때 필요한 배열
		
		ans = Integer.MAX_VALUE;	// 배열 A의 값의 최솟값(최종 출력값) 초기화 
		
		operationPermutation(0);
		
		System.out.println(ans);
	}
	
	
	static void operationPermutation(int depth) {	// boolean[] visited, 
		if(depth == K) {
			// opNumPerm에 연산 사용 순서 순열 완성됨
			// operations에서 opNumPerm의 순서대로 회전 연산 선택 후 회전 수행
			int r, c, s;
			for(int p = 0; p < K; p++) {			
				r = operations[opNumPerm[p]][0];	// 선택한 회전 연산 순열 순서에 맞게 회전 연산 정보 받아옴
				c = operations[opNumPerm[p]][1];
				s = operations[opNumPerm[p]][2];
				int startR = r-s;
				int endR = r+s;
				int startC = c-s;
				int endC = c+s;
				int midR = (startR + endR) / 2;
				int midC = (startC + endC) / 2;
				//boolean isOdd = false;	// 회전하는 구역이 홀수 x 홀수인지 확인하는 flag
				//if((endR - startR + 1) % 2 == 1 && (endC - startC + 1) % 2 == 1) isOdd = true;
				
				// 시계 방향 회전 수행
				for(int i = startR; i <= endR; i++) {
					for(int j = startC; j <= endC; j++) {
						// 대각선을 기준으로 4개 영역으로 나눔
						//try {
							if(j > i+(endC-endR) && j >= -i+(endC+1)-(startR-1) && j > (startC+endC)/2) {	// #4 #3 #가운데
								newArr[i + di[1]][j + dj[1]] = arr[i][j];			// 아래로
//								System.out.println("down i: " + i + ", j: " + j);
							} else if(i+(startC-startR) > j && j <= -i+(endR+1)+(startC-1)) { // #1 #2
								newArr[i + di[0]][j + dj[0]] = arr[i][j];			// 위로
//								System.out.println("up i: " + i + ", j: " + j);
							} else if(i <= (startR+endR)/2) {
								if(!(i == midR && j == midC)) {		// 한가운데 값(이동 금지) 		홀수x홀수이면 	isOdd == true && 
									newArr[i + di[3]][j + dj[3]] = arr[i][j];			// 오른쪽으로
//									System.out.println("right i: " + i + ", j: " + j);
								}
							} else {
								newArr[i + di[2]][j + dj[2]] = arr[i][j];			// 왼쪽으로
//								System.out.println("left i: " + i + ", j: " + j);
							}
//						} catch(Exception e) {
//							e.printStackTrace();
//							System.out.println("===== 오류가 생긴 부분 i: " + i + ", j: " + j);
//						}
					}
				}
				
				//if (isOdd = true) {		// 한가운데 값 원상복귀
				newArr[midR][midC] = arr[midR][midC];
				//}
				
				// 범위 밖의 배열은 그대로 저장
				// 상단
				for (int i = 0 ; i < startR; i++) {
					for (int j = 0; j <= M; j++) {
						newArr[i][j] = arr[i][j];
					}
				}
				
				// 하단
				for (int i = endR + 1 ; i <= N; i++) {
					for (int j = 0; j <= M; j++) {
						newArr[i][j] = arr[i][j];
					}
				}
				
				// 좌, 우
				for(int i = startR; i <= endR; i++) {
					for(int j = 0; j < startC; j++) {
						newArr[i][j] = arr[i][j];
					}
					for(int j = endC + 1; j <= M; j++) {
						newArr[i][j] = arr[i][j];
					}
				}
				
				for(int k = 0; k < newArr.length; k++) {	// arr <- newArr 복사
					System.arraycopy(newArr[k], 0, arr[k], 0, newArr[k].length);
				}
				
				// 테스트 출력
				System.out.println("============================");
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						System.out.print(arr[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("============================");
			}
			// 회전 연산 K개 수행 종료
			
			// 현재 연산 순서대로 회전한 배열의 각 행의 값(sum)이 최솟값(ans)보다 더 작으면 ans에 저장
			for(int i = 1; i <= N; i++) {
				int sum = 0;	// 한 행의 합
				for(int j = 1; j <= M; j++) {
					sum += arr[i][j];
				}
				if(sum < ans) ans = sum;
			}
			
			// 배열 원상복귀
			for(int k = 0; k < originArr.length; k++) {	// arr <- originArr 복사
				System.arraycopy(originArr[k], 0, arr[k], 0, originArr[k].length);
			}
			
//			System.out.println("=========배열 원복============");
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= M; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("============================");
		}
		
		for(int i = 0; i < K; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				opNumPerm[depth] = opNum[i];
				operationPermutation(depth + 1);	// visited, 
				visited[i] = false;
			}
		}
	}
}
