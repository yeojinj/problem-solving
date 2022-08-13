package D3;
import java.util.Scanner;

class D3_1873_상호의배틀필드_정여진 {
	
	static int playerR;	// 전차의 행 위치
	static int playerC;	// 전차의 열 위치
	
	public static void main(String args[]) throws Exception	{
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++)	{
			int H = sc.nextInt();	// 높이
			int W = sc.nextInt();	// 너비
			
			char[][] map = new char[H][W];
			sc.nextLine();
			
			// 맵 구성 요소 입력 & 전차 위치 찾기
			for (int i = 0; i < map.length; i++) {
				String str = sc.nextLine();
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						playerR = i;
						playerC = j;
					}
				}
			}
			
			int N = sc.nextInt();	// 사용자 입력의 개수
			sc.nextLine();
			
			String input = sc.nextLine();	// 사용자 입력
			
			for (int i = 0; i< input.length(); i++) {
				char c = input.charAt(i);
				if (c == 'S') {
					shoot(map);
				} else {
					move(map, c);
				}
			}
			
			// 출력
			System.out.print("#" + test_case + " ");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
		}
	}
	
	public static void move(char[][] map, char c) {
		if(c == 'U') {
			map[playerR][playerC] = '^';	// 전차가 바라보는 방향을 위쪽으로 바꾸고
			// 한 칸 위의 칸이 평지라면 그 칸으로 이동
			if (playerR - 1 >= 0 && map[playerR - 1][playerC] == '.') {	
				map[playerR][playerC] = '.';
				map[--playerR][playerC] = '^';
			}
		} else if (c == 'D') {
			map[playerR][playerC] = 'v';	// 전차가 바라보는 방향을 아래쪽으로 바꾸고
			// 한 칸 아래의 칸이 평지라면 그 칸으로 이동
			if (playerR + 1 < map.length && map[playerR + 1][playerC] == '.') {	
				map[playerR][playerC] = '.';
				map[++playerR][playerC] = 'v';
			}
		} else if (c == 'L') {
			map[playerR][playerC] = '<';	// 전차가 바라보는 방향을 왼쪽으로 바꾸고
			// 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동
			if (playerC - 1 >= 0 && map[playerR][playerC - 1] == '.') {	
				map[playerR][playerC] = '.';
				map[playerR][--playerC] = '<';
			}
		} else if (c == 'R') {
			map[playerR][playerC] = '>';	// 전차가 바라보는 방향을 오른쪽으로 바꾸고
			// 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동
			if (playerC + 1 < map[playerR].length && map[playerR][playerC + 1] == '.') {	
				map[playerR][playerC] = '.';
				map[playerR][++playerC] = '>';
			}
		}
	}
	
	public static void shoot(char[][]map) {
		char dir = map[playerR][playerC];
		if (dir == '^' && playerR - 1 >= 0) {
			for (int i = playerR - 1; i >= 0; i--) {
				if (map[i][playerC] == '*') {	// 벽돌로 만들어진 벽
					map[i][playerC] = '.';		// 포탄이 부딪히면 벽 파괴되어 평지가 됨
					break;
				}
				if (map[i][playerC] == '#') {	// 강철로 만들어진 벽
					break;						// 아무 일도 일어나지 않는다.
				}
				// 게임 맵 밖으로 포탄이 나가면 아무 일도 일어나지 않는다.
			}
		} else if (dir == 'v' && playerR + 1 < map.length) {
			for (int i = playerR + 1; i < map.length; i++) {
				if (map[i][playerC] == '*') {	// 벽돌로 만들어진 벽
					map[i][playerC] = '.';		// 포탄이 부딪히면 벽 파괴되어 평지가 됨
					break;
				}
				if (map[i][playerC] == '#') {	// 강철로 만들어진 벽
					break;						// 아무 일도 일어나지 않는다.
				}
				// 게임 맵 밖으로 포탄이 나가면 아무 일도 일어나지 않는다.
			}
		} else if (dir == '<' && playerC - 1 >= 0) {
			for (int j = playerC - 1; j >= 0; j--) {
				if (map[playerR][j] == '*') {	// 벽돌로 만들어진 벽
					map[playerR][j] = '.';		// 포탄이 부딪히면 벽 파괴되어 평지가 됨
					break;
				}
				if (map[playerR][j] == '#') {	// 강철로 만들어진 벽
					break;						// 아무 일도 일어나지 않는다.
				}
				// 게임 맵 밖으로 포탄이 나가면 아무 일도 일어나지 않는다.
			}
		} else if (dir == '>' && playerC + 1 < map[playerR].length) {
			for (int j = playerC + 1; j < map[playerR].length; j++) {
				if (map[playerR][j] == '*') {	// 벽돌로 만들어진 벽
					map[playerR][j] = '.';		// 포탄이 부딪히면 벽 파괴되어 평지가 됨
					break;
				}
				if (map[playerR][j] == '#') {	// 강철로 만들어진 벽
					break;						// 아무 일도 일어나지 않는다.
				}
				// 게임 맵 밖으로 포탄이 나가면 아무 일도 일어나지 않는다.
			}
		}
	}
}


/*
문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽 -> 포탄이 부딪히면 벽 파괴되어 평지가 됨
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

문자	동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

1
3 7
***....
*-..#**
#<.****
23
SURSSSSUSLSRSSSURRDSRDS

*/