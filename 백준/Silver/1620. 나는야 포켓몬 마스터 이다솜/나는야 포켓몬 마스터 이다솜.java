import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도감에 수록되어 있는 포켓몬의 개수
        int M = Integer.parseInt(st.nextToken());   // 내가 맞춰야 하는 문제의 개수
        String[] numMap = new String[N + 1];                    // 번호로 이름 찾기
        HashMap<String, Integer> nameMap = new HashMap<>();     // 이름으로 번호 찾기

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            numMap[i] = name;
            nameMap.put(name, i);
        }

        for (int i = 1; i <= M; i++) {
            String q = br.readLine();
            try {
                int num = Integer.parseInt(q);
                System.out.println(numMap[num]);
            } catch (NumberFormatException e) {
                System.out.println(nameMap.get(q));
            }
        }

    }
}
