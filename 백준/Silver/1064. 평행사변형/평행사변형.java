import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Point[] points = new Point[3];
        for (int i = 0; i < 3; i++) {
            points[i] = new Point();
            points[i].x = Integer.parseInt(st.nextToken());
            points[i].y = Integer.parseInt(st.nextToken());
        }

        if (isStraight(points[0], points[1], points[2])) {
            sb.append(-1);
        } else {
            double ab = getLength(points[0], points[1]);
            double bc = getLength(points[1], points[2]);
            double ac = getLength(points[0], points[2]);

            double maxRound = 2 * Math.max(ab, Math.max(bc, ac));
            double minRound = 2 * Math.min(ab, Math.min(bc, ac));

            sb.append(maxRound - minRound);
        }


        System.out.println(sb);
    }

    static boolean isStraight(Point a, Point b, Point c) {
        if ((double) (a.x - b.x) * (b.y - c.y) == (double) (b.x - c.x) * (a.y - b.y)) {
            return true;
        }
        return false;
    }

    static double getLength(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}