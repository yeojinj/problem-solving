package bronze;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3040_백설공주와일곱난쟁이_정여진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[] = new int[9];
		
		int sum9 = 0;		// 난쟁이 9명의 숫자 합
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine()); // 입력 받기
			sum9 += arr[i]; // 숫자 9개의 합 구하기
		}
		
		int sum2 = sum9 - 100;		// 일곱 난쟁이가 아닌 2명의 숫자 합
		
		for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                int sum = arr[i] + arr[j];
                if (sum == sum2) {	// 2명의 숫자 합이 일치할 때
                    for (int k = 0; k < 9; k++) {
                        if (k != i && k != j) 	// i, j 난쟁이만 빼고 출력
                        	System.out.println(arr[k]);
                    }
                }
            }
        }
	}
}
