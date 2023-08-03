import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 작업장의 수

        int[] dpA = new int[N + 2];     // Ai 작업장까지 와서 작업 완료되기까지 걸리는 시간
        int[] dpB = new int[N + 2];     // Bi 작업장까지 와서 작업 완료되기까지 걸리는 시간
        int atoB = 0;   // Ai 작업장에서 Bi-1 작업장까지 이동시간
        int btoA = 0;   // Bi 작업장에서 Ai-1 작업장까지 이동시간
        int tempA, tempB;

        // 1번째 줄
        st = new StringTokenizer(br.readLine());
        dpA[1] = Integer.parseInt(st.nextToken());
        dpB[1] = Integer.parseInt(st.nextToken());
        if (N > 1) {
            atoB = Integer.parseInt(st.nextToken());
            btoA = Integer.parseInt(st.nextToken());
        }

        // 2 ~ N번째 줄
        int i = 2;
        while (i <= N) {
            st = new StringTokenizer(br.readLine());
            tempA = Integer.parseInt(st.nextToken());   // Ai 작업장의 작업시간
            tempB = Integer.parseInt(st.nextToken());   // Bi 작업장의 작업시간

            dpA[i] = Math.min(dpA[i - 1], dpB[i - 1] + btoA) + tempA;   // 여기서 btoA는 Bi-1 작업장에서 Ai 작업장까지 이동시간
            dpB[i] = Math.min(dpB[i - 1], dpA[i - 1] + atoB) + tempB;

            if (i == N) break;
            atoB = Integer.parseInt(st.nextToken());
            btoA = Integer.parseInt(st.nextToken());

            i++;
        }

        // 출력
        System.out.println(Math.min(dpA[N], dpB[N]));

    }
}