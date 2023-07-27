class Solution {
    static int[][] dp;      // dp[알고력][코딩력] = 걸리는시간
    
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        
        int maxAlp = -1;    // 모든 문제를 풀 수 있는 알고력
        int maxCop = -1;    // 모든 문제를 풀 수 있는 코딩력
        
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(problems[i][0], maxAlp);
            maxCop = Math.max(problems[i][1], maxCop);
        }
        
        if (alp >= maxAlp && cop >= maxCop) {
            return answer;
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        // 초기화
        dp = new int[maxAlp + 2][maxCop + 2];
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);    // 알고리즘 공부
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);    // 코딩 공부
                
                // 현재 풀 수 있는 문제 중 하나 풀기
                for (int[] p : problems) {
                    if (p[0] <= i && p[1] <= j) {     // 풀 수 있는 문제이면
                        int incrAlp = i + p[2];     // 해당 문제를 풀었을 때 증가된 알고력
                        int incrCop = j + p[3];     // 해당 문제를 풀었을 때 증가된 코딩력
                        int cost = p[4];            // 해당 문제를 푸는데 드는 시간
                        
                        incrAlp = Math.min(incrAlp, maxAlp);
                        incrCop = Math.min(incrCop, maxCop);
                        
                        dp[incrAlp][incrCop] = Math.min(dp[incrAlp][incrCop], dp[i][j] + cost);
                    }
                }
                
            }
        }
        
        for (int i = 0; i <= maxAlp; i++) {
            for (int j = 0; j <= maxCop; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        
        answer = dp[maxAlp][maxCop];
        
        return answer;
    }
}