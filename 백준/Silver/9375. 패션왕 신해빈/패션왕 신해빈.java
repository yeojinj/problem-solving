import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스의 수
        for (int tc = 0; tc < T; tc++) {
            HashMap<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());    // 의상의 수
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();           // 의상의 이름 (필요 없음
                String type = st.nextToken();           // 의상의 종류

                // 해시맵에 의상 종류 별로 개수 카운트해서 넣기
                if (map.containsKey(type)) {
                    int cnt = map.get(type);
                    map.put(type, cnt + 1);
                } else {
                    map.put(type, 1);
                }
            }

            // 의상 종류 face가 1번부터 3번까지 3개라고 하면 face는 하나만 입을 수 있으므로
            // {0(아무것도 선택 안함), 1, 2, 3} 4가지 경우
            // 종류 별로 경우의 수를 곱셈 한 다음 전부 {0}인 경우, 즉 아무것도 안 입은 경우(1가지)를 빼주면 됨
            int ans = 1; // 곱셈이므로 1로 시작
            for (int cnt : map.values()) {
                ans *= (cnt + 1);
            }
            ans--;      // 아무것도 안 입은 경우
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

}