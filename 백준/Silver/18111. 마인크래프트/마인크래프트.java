import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 세로
        int M = Integer.parseInt(st.nextToken());   // 가로
        int B = Integer.parseInt(st.nextToken());   // 인벤토리 블록 수
        HashMap<Integer, Integer> map = new HashMap<>();

        int minH = 257, maxH = -1;    // 최소 높이, 최대 높이
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int h = Integer.parseInt(st.nextToken());
                if (map.containsKey(h)) {
                    int cnt = map.get(h);
                    map.put(h, cnt + 1);
                } else {
                    map.put(h, 1);
                }
                minH = Math.min(minH, h);
                maxH = Math.max(maxH, h);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int minTimeH = 0;
        for (int i = minH; i <= maxH; i++) {
            int b = B;      // 인벤토리 블록 수
            int time = 0;   // 걸린 시간

            // 높이 nh -> i로 땅 고르기
            for (Integer nh : map.keySet()) {
                int cnt = map.get(nh);                  // 높이가 nh인 땅의 개수
                int gap = cnt * (Math.abs(i - nh));     // 땅의 개수 * 차이나는 높이
                if (nh > i) {          // 블록 제거 (2초)
                    time += gap * 2;
                    b += gap;
                } else if (nh < i) {   // 블록 놓기 (1초)
                    time += gap;
                    b -= gap;
                }
            }

            if (b >= 0) {
                // 걸린 시간과 최소 시간 비교
                if (minTime > time) {
                    minTime = time;
                    minTimeH = i;
                } else if (minTime == time) {
                    if (minTimeH < i) {
                        minTimeH = i;
                    }
                }
            }
        }

        System.out.println(minTime + " " + minTimeH);
    }
}
