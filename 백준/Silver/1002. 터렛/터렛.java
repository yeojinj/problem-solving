import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());   // 테스트 케이스의 수
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());  // 조규현의 좌표
            int r1 = Integer.parseInt(st.nextToken());  // 조규현과 류재명 거리
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());  // 백승환의 좌표
            int r2 = Integer.parseInt(st.nextToken());  // 백승환과 류재명 거리

            // 조규현과 백승환 거리
            double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

            if (dist == 0) {    // 조규현과 백승환이 같은 위치에 있을 때
                if (r1 == r2) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                if (dist > r1 + r2) {
                    sb.append(0).append("\n");
                } else if (dist == r1 + r2) {
                    sb.append(1).append("\n");
                } else {
                    if (dist < Math.abs(r1 - r2)) {
                        sb.append(0).append("\n");
                    } else if (dist == Math.abs(r1 - r2)) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(2).append("\n");
                    }
                }
            }
        }

        System.out.print(sb);
    }

}