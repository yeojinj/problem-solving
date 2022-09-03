package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1541_잃어버린괄호_정여진 {
	static String[] str;
	static ArrayList<Integer> operators;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().split("-");
		int result = 0;
		
		for(int i = 0; i < str.length; i++) {
			int sum = 0;
			String[] addition = str[i].split("\\+");
			
			for(int j = 0; j < addition.length; j++) {
				sum += Integer.parseInt(addition[j]);
			}
			
			if(i == 0) {
				result = sum;
			} else {
				result -= sum;
			}
		}
		
		System.out.println(result);
	}
}
