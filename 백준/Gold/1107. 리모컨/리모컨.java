import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());   // 이동하려고 하는 채널
        int M = Integer.parseInt(br.readLine());   // 고장난 버튼의 개수

        // 고장난 버튼 체크
        broken = new boolean[10];
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int n = Integer.parseInt(st.nextToken());
                broken[n] = true;
            }
        }

        int min = Math.abs(N - 100);        // 지금 보고 있는 채널(100번) 기준으로 값 초기화
        for (int i = 0; i <= 999_999; i++) {
            if (!isBroken(i)) {
                int count = Integer.toString(i).length();   // 처음에 채널 i번 자리수만큼 버튼 누름
                count += Math.abs(N - i);                   // 채널 N번까지 이동
                min = Math.min(min, count);
            }
        }

        sb.append(min);

        System.out.println(sb);
    }

    // 채널 x번을 누를 수 있는지(고장난 버튼 있는지)
    public static boolean isBroken(int x) {
        String str = Integer.toString(x);
        for (int i = 0; i < str.length(); i++) {
            if (broken[str.charAt(i) - '0']) {
                return true;
            }
        }
        return false;
    }

}
