package gold;

import java.util.Scanner;

public class BJ_14921_용액합성하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int arr[] = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int start = 0;
		int end = N - 1;
		int gap = Integer.MAX_VALUE;	// 0에 가장 가까운 특성값

		while (start < end) {
			int temp = arr[start] + arr[end];
			if (Math.abs(temp) < Math.abs(gap)) {
				gap = temp;
			}
			if (temp > 0) {
				end--;
			} else if (temp < 0) {
				start++;
			} else {
				gap = 0;
				break;
			}
		}

		System.out.println(gap);
	}

}
