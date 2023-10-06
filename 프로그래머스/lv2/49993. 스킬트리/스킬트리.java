class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            String skillTree = skill_trees[i];  // 스킬트리 하나씩 탐색
            int skillIdx = 0;   // 몇 번째 스킬을 먼저 써야하는지
            boolean isValid = true;    // 현재 스킬트리가 가능한 건지 여부
            
            for (int j = 0; j < skillTree.length(); j++) {  // 현재 스킬트리 탐색
                char s = skillTree.charAt(j);
                for (int k = skillIdx; k < skill.length(); k++) {  // 선행 스킬 순서 탐색
                    if (skill.charAt(k) == s) {     // 선행 스킬 순서에 있는 스킬 사용할 차례임
                        if (k == skillIdx) {        // 선행 스킬 순서에 맞게 썼을 경우
                            skillIdx++;
                        } else {                    // 선행 스킬 순서에 안 맞게 썼을 경우
                            isValid = false;
                            break;
                        }
                    }
                }
                
                if (!isValid) {
                    break;
                }
            }
            
            if (isValid) {
                answer++;
            }
        }
        
        return answer;
    }
}