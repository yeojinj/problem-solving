import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] stageInfo = new int[N];     // 각 스테이지에 탈락한 사람 수
        int people = stages.length;       // 사용자 수
        
        for (int i = 0; i < people; i++) {
            if (stages[i] - 1 >= N) continue;   // 마지막 스테이지까지 클리어 한 사용자
            stageInfo[stages[i] - 1]++;   // 1번 스테이지 -> index 0
        }
        
        List<Stage> stageList = new ArrayList<>();   // 각 스테이지의 번호와 실패율
        
        for (int i = 0; i < N; i++) {
            double failRate = (double) stageInfo[i] / people;
            stageList.add(new Stage(i + 1, failRate));
            people -= stageInfo[i];
        }
        
        Collections.sort(stageList);
        
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            answer[i] = stageList.get(i).num;
        }
        
        return answer;
    }
}

class Stage implements Comparable<Stage>{
    int num;
    double failRate;
    
    Stage (int num, double failRate) {
        this.num = num;
        this.failRate = failRate;
    }
    
    @Override
    public int compareTo(Stage s) {
        if (s.failRate > this.failRate) return 1;
        else if (s.failRate < this.failRate) return -1;
        else return 0;
    }
}