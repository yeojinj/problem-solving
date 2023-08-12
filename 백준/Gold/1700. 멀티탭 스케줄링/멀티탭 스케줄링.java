import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 멀티탭 구멍의 개수
        int K = Integer.parseInt(st.nextToken());   // 전기 용품의 총 사용횟수

        ArrayList<Integer> order = new ArrayList<>();   // 전기 용품 사용 순서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int x = Integer.parseInt(st.nextToken());   // 사용할 전기 용품 번호
            order.add(x);
        }

        int pullCnt = 0;    // 플러그를 빼는 최소 횟수 (정답)

        ArrayList<Integer> power = new ArrayList<>();   // 멀티탭에 꽂힌 전기 용품
        while (!order.isEmpty()) {
            int x = order.remove(0);

            // 사용할 전기 용품이 이미 꽂혀있지 않으면

            if (!power.contains(x)) {
                if (power.size() == N) {         // 멀티탭에 자리가 없으면
                    // 다른 걸 뽑고 그 자리에 꽂아야 함
                    int pullPowerIdx = -1;   // 뽑을 것이 멀티탭 몇 번째에 꽂혀있는지
                    int maxOrder = -1;
                    for (int j = 0; j < N; j++) {   // 멀티탭에 꽂혀있는 전기 용품들 중에
                        // 1) 나중에 사용되지 않거나, 2) 최대한 나중에 사용되는 것 고르기
                        int num = power.get(j);
                        int numOrder = order.indexOf(num);
                        if (numOrder == -1) {       // 1)
                            pullPowerIdx = j;
                            break;
                        } else {                    // 2)
                            if (maxOrder < numOrder) {
                                maxOrder = numOrder;
                                pullPowerIdx = j;
                            }
                        }
                    }

                    power.remove(pullPowerIdx);     // 뽑기
                    pullCnt++;
                }
                // x번 전기 용품 멀티탭에 꽂기
                power.add(x);
            }

            // 사용할 전기 용품이 이미 꽂혀있으면 아무것도 안함

        }

        System.out.println(pullCnt);
    }
}
