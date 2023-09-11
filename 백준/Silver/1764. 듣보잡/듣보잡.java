import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 듣도 못한 사람의 수
        int M = Integer.parseInt(st.nextToken());   // 보도 못한 사람의 수
        HashSet<String> set = new HashSet<>();      // 듣도 못한 사람 집합

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        int cnt = 0;
        List<String> names = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (set.contains(name)) {
                cnt++;
                names.add(name);
            }
        }
        Collections.sort(names);

        System.out.println(cnt);
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }

    }
}
