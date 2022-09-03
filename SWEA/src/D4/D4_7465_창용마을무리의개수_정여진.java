package D4;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class D4_7465_창용마을무리의개수_정여진 {
    static int N, M;
    static int[] root;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            root = new int[N + 1];	// 1~N만 사용
            for(int i = 1; i <= N; i++) {
            	root[i] = i;
            }
            
            for(int i = 0; i < M; i++) {    
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
             
            // 몇 개의 무리가 존재하는지 계산
            int ans = 0;
            for(int i = 1; i <= N; i++) {
            	if(root[i] == i) {	// 무리의 root 개수 세기
            		ans++;
            	}
            }
             
            bw.write("#" + tc + " " + ans +'\n');
        }
        bw.close(); 
    }
    
    static int getRoot(int x) {
    	if (root[x] == x) return x;
    	else return root[x] = getRoot(root[x]);
    }
    
    static void union(int x, int y) {
    	x = getRoot(x);
    	y = getRoot(y);
    	
    	if(x < y) root[y] = x;
    	else root[x] = y;
    }
}