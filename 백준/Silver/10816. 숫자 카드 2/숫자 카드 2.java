import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());   // 숫자 카드의 개수
        HashMap<Integer, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (map.containsKey(x)) {
                int cnt = map.get(x);
                map.put(x, cnt + 1);
            } else {
                map.put(x, 1);
            }
        }

        int M = Integer.parseInt(br.readLine());   // 찾을 숫자의 개수
        st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (map.containsKey(x)) {
                sb.append(map.get(x) + " ");
            } else {
                sb.append(0 + " ");
            }
        }

        System.out.println(sb.toString());
    }

}