package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());       // 여학생의 수
        int M = Integer.parseInt(st.nextToken());       // 남학생의 수
        int K = Integer.parseInt(st.nextToken());       // 인턴쉽에 참여해야하는 인원

        int team = 0;      // 팀 수

        while (true) {
            N -= 2;
            M -= 1;
            if (N + M >= K && N >= 0 && M >= 0) {
                team++;
                continue;
            }
            else {
                break;
            }
        }

        System.out.println(team);
    }

}
