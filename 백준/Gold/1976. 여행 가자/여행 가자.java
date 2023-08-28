import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int root[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());     // 도시의 수
        int M = Integer.parseInt(br.readLine());     // 여행 계획에 속한 도시의 수

        root = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int adj = Integer.parseInt(st.nextToken());
                if (adj == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = Integer.parseInt(st.nextToken());
        root = find(root);
        boolean ans = true;
        for (int i = 1; i < M; i++) {
            int plan = Integer.parseInt(st.nextToken());
            plan = find(plan);
            if (root != plan) {
                ans = false;
                break;
            }
        }

        if (ans) System.out.println("YES");
        else System.out.println("NO");
    }

    static int find(int x) {
        if (root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) root[x] = y;
        else root[y] = x;
    }
}