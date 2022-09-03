package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {
    int r;		// CCTV 위치(r, c)
    int c;
    int num;	// CCTV 번호
    int n;		// CCTV가 회전할 수 있는 경우의 수

    public CCTV(int r, int c, int num) {
        this.r = r;
        this.c = c;
        this.num = num;
        this.setN(num);
    }

	public void setN(int num) {		// CCTV 번호에 따라 회전할 수 있는 경우의 수 저장
        switch(num) {
        case 1:
        case 3:
        case 4:
            this.n = 4;
            break;
        case 2:
            this.n = 2;
            break;
        case 5:
            this.n = 1;
            break;
        }
	}
}
 
public class BJ_15683_감시_정여진 {
    static int N, M, ans;
    static int[][] office;
    static int[] dr = { 0, -1, 0, 1 };	// 우 상 좌 하
    static int[] dc = { 1, 0, -1, 0 };

    static ArrayList<CCTV> cctv = new ArrayList<>();
    static boolean[][] visited = new boolean[9][5];
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N + 2][M + 2];
        ans = N * M;
 
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= office[i][j] && office[i][j] <= 5)
                    cctv.add(new CCTV(i, j, office[i][j]));
            }
        }
        
        perm(0);
        
        System.out.println(ans);
    }
 
    public static void perm(int depth) {
        if (depth == cctv.size()) {
            ans = Math.min(ans, countBlindSpot());		// 사각 지대의 최소 크기 갱신
            return;
        }
 
        for (int i = 0; i < cctv.get(depth).n; i++) {	// 현재 선택된 cctv가 회전할 수 있는 경우의 수만큼 반복
            if (visited[depth][i]) continue;
            visited[depth][i] = true;
            setCCTV(cctv.get(depth).r, cctv.get(depth).c, cctv.get(depth).num, i, 9);
            perm(depth + 1);
            visited[depth][i] = false;
            setCCTV(cctv.get(depth).r, cctv.get(depth).c, cctv.get(depth).num, i, -9);
        }
    }
 
    /** 사각 지대의 크기 계산 */
    public static int countBlindSpot() {
        int count = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                if (office[i][j] == 0) count++;
        return count;
    }
 
    public static void setCCTV(int x, int y, int cam, int d, int status) {
        rotate(x, y, d, status);
        if (cam == 3 || cam == 4 || cam == 5)
            rotate(x, y, d + 1, status);
        if (cam == 2 || cam == 4 || cam == 5)
            rotate(x, y, d + 2, status);
        if (cam == 5)
            rotate(x, y, d + 3, status);
    }
 
    /** CCTV 회전 */
    public static void rotate(int r, int c, int dir, int status) {
        int R = r, C = c, nr, nc;
        while(true) {
            nr = R + dr[dir % 4];
            nc = C + dc[dir % 4];
            if (nr < 1 || nr > N || nc < 1 || nc > M || office[nr][nc] == 6) break;
 
            office[nr][nc] += status;
            R = nr;
            C = nc;
        }
    }
}