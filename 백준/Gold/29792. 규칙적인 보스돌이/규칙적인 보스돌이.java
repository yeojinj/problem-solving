import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Boss {
	Long hp;
	Long meso;

	Boss(Long x, Long y) {
		this.hp = x;
		this.meso = y;
	}
}

public class Main {
	
    static final int TIME = 15 * 60;    // 한 캐릭터 당 최대 시간
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 보유한 캐릭터의 개수
		int M = Integer.parseInt(st.nextToken());	// 하루에 사용할 캐릭터의 개수
		int K = Integer.parseInt(st.nextToken());	// 보스의 가짓수
		Long[] damage = new Long[N];				// 각 캐릭터의 데미지
		
		for (int i = 0; i < N; i++) {
			damage[i] = Long.parseLong(br.readLine());	// 1초에 가하는 데미지
		}
		Arrays.sort(damage, Collections.reverseOrder());
		
		ArrayList<Boss> arr = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			Long P = Long.parseLong(st.nextToken());	// 보스의 체력
			Long Q = Long.parseLong(st.nextToken());	// 드랍하는 메소
			arr.add(new Boss(P, Q));
		}
		
		int totalMeso = 0;
		Long maxMeso;
		for (int i = 0; i < M; i++) {
			maxMeso = 0L;
			for (int j = 0; j < (1 << K); j++) {
				Long nMeso = 0L;
				Long remainDamage = 0L;
				for (int k = 0; k < K; k++) {	// 보스
					if ((j & (1 << k)) != 0) {
						remainDamage += (arr.get(k).hp / damage[i]) * damage[i] + (arr.get(k).hp % damage[i] == 0 ? 0 : damage[i]);
						nMeso += arr.get(k).meso;
					}
				}
				if (remainDamage <= damage[i] * TIME)
					maxMeso = Math.max(maxMeso, nMeso);
			}
			totalMeso += maxMeso;
		}
		
		System.out.println(totalMeso);
	}

}