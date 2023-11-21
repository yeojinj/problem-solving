class Solution {
    public int solution(int n, int k) {
        String kForm = convertToKForm(n, k);    // k진수
        System.out.println(kForm);
        String[] slices = kForm.split("0");     // 0을 기준으로 쪼갬
        int answer = 0;
        
        for (String slice : slices) {
            if (!slice.equals("")) {
                long P = Long.parseLong(slice);
                if (isPrime(P)) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    // 소수 판별
    private boolean isPrime(long n){
        if (n <= 1) {
            return false;
        } else if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    // k진법으로 변환
    private String convertToKForm(int n, int k) {
        String result = "";
        
        while (n != 0) {
            result = n % k + result;
            n /= k;
        }
        
        return result;
    }
}