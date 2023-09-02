import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int docking[], root[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());   // 게이트의 수
        int P = Integer.parseInt(br.readLine());   // 비행기의 수

        docking = new int[G + 1];
        root = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            root[i] = i;
        }

        int ans = 0;
        for (int i = 1; i <= P; i++) {
            int g = Integer.parseInt(br.readLine());
            while (g > 1) {
                if (docking[g] > 0) {
                    g = find(g);
                } else {
                    break;
                }
            }
            if (docking[g] > 0) {
                break;
            } else {
                docking[g] = i;
                if (g - 1 >= 1) {
                    union(g - 1, g);
                }
                ans++;
            }
        }

        System.out.println(ans);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) root[y] = x;
        else root[x] = y;
    }

    static int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

}