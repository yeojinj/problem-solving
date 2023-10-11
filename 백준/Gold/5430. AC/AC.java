import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());    // 테스트 케이스의 수

        for (int tc = 0; tc < T; tc++) {
            StringBuilder sb = new StringBuilder();
            String p = br.readLine();   // 수행할 함수
            int n = Integer.parseInt(br.readLine());    // 배열에 들어있는 수의 개수
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine(), "[],");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            boolean error = false;
            boolean order = true;   // true 면 배열 순서 그대로, false 면 거꾸로
            int first = 0, last = n - 1;    // 배열의 처음과 끝 인덱스
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);
                switch (c) {
                    case 'R':   // 뒤집기
                        order = !order;
                        break;
                    case 'D':   // 버리기
                        if (order) {
                            if (first > last) {
                                error = true;
                                break;
                            }
                            first++;
                        } else {
                            if (first > last) {
                                error = true;
                                break;
                            }
                            last--;
                        }
                        break;
                }
            }
            if (error) {
                sb.append("error").append('\n');
            } else {
                sb.append('[');
                if (first <= last) {
                    if (order) {
                        for (int i = first; i < last; i++) {
                            sb.append(arr[i]).append(',');
                        }
                            sb.append(arr[last]);
                    } else {
                        for (int i = last; i > first; i--) {
                            sb.append(arr[i]).append(',');
                        }
                            sb.append(arr[first]);
                    }
                }
                sb.append(']').append('\n');
            }
            System.out.print(sb);
        } // 테스트 케이스 끝

    }
}