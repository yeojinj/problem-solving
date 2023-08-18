import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Line implements Comparable<Line> {
    int x;
    int y;

    public Line(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Line o) {
        if (this.x == o.x) {
            return this.y - o.y;
        } else {
            return this.x - o.x;
        }
    }
}

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());        // 선을 그은 횟수
        Line lines[] = new Line[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines[i] = new Line(x, y);
        }

        Arrays.sort(lines);

        int ans = 0;        // 선의 총 길이
        int start = lines[0].x, end = lines[0].y;   // 선 시작점, 끝점
        for (int i = 1; i < N; i++) {
            if (end < lines[i].x) {     // 선이 끊김
                ans += (end - start);
                start = lines[i].x;
                end = lines[i].y;
            } else {                    // 선이 이어짐
                if (end < lines[i].y) { // 선 연장
                    end = lines[i].y;
                }
            }
        }

        ans += (end - start);

        System.out.println(ans);
    }

}
