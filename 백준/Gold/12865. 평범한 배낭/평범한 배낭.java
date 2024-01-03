import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Gem {
    int w;  // 무게
    int v;  // 가치
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 물건 수
        int K = Integer.parseInt(st.nextToken());   // 최대 무게

        Gem[] gems = new Gem[N + 1];    // (1 ~ N)

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            gems[i] = new Gem();
            gems[i].w = Integer.parseInt(st.nextToken());   // i번 물건의 무게
            gems[i].v = Integer.parseInt(st.nextToken());   // i번 물건의 가치
        }

        int[] dpdp = new int[K + 1];                // O(NK) 이하, 메모리 절약
        for (int i = 1; i <= N; i++) {
            for (int k = K; k >= gems[i].w; k--) {
                dpdp[k] = Math.max(dpdp[k], dpdp[k - gems[i].w] + gems[i].v);
            }
        }

        System.out.println(dpdp[K]);

    }
}