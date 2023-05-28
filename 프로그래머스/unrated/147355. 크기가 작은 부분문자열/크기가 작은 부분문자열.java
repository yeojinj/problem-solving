class Solution {
    public int solution(String t, String p) {
        int fullSize = t.length();
        int subSize = p.length();
        long num = Long.parseLong(p);
        int answer = 0;
        
        for (int i = 0; i <= fullSize - subSize; i++) {
            long x = Long.parseLong(t.substring(i, i + subSize));
            if (x <= num) {
                answer++;
            }
        }
        
        return answer;
    }
}