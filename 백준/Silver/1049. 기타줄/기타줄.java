import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 끊어진 기타줄의 개수
        int M = Integer.parseInt(st.nextToken());   // 기타줄 브랜드

        int minPack = 1001, minEach = 1001;     // 6줄 패키지 최소 가격, 낱개 최소 가격
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pricePack = Integer.parseInt(st.nextToken());
            minPack = Math.min(minPack, pricePack);
            int priceEach = Integer.parseInt(st.nextToken());
            minEach = Math.min(minEach, priceEach);
        }

        int price = 0;
        if (minEach * 6 > minPack) {    // 6개를 살 때 패키지 가격이 더 싸면
            price += (N / 6) * minPack;
            price += Math.min((N % 6) * minEach, minPack);
        } else {                        // 낱개로 6개를 사는 게 더 싸면
            price += N * minEach;
        }

        System.out.println(price);
    }
}