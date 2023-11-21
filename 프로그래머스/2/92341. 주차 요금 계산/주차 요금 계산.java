import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> carInTime = new HashMap<>();    // key: 차량 번호, value: 입차 시각
        TreeMap<String, Integer> carParkingTime = new TreeMap<>(); // key: 차량 번호, vaule: 누적 주차 시간(분)
        
        // 누적 주차 시간 계산
        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String carNum = st.nextToken();
            String inOut = st.nextToken();
            
            if (inOut.equals("IN")) {
                carInTime.put(carNum, time);
            } else {
                String inTime = carInTime.get(carNum);
                carInTime.remove(carNum);
                int parkingTime = calcTimeDiff(inTime, time);
                carParkingTime.put(carNum, carParkingTime.getOrDefault(carNum, 0) + parkingTime);
            }
        }
        
        // 출차 내역 없으면 23:59 출차로 간주
        for (String carNum : carInTime.keySet()) {
            String inTime = carInTime.get(carNum);
            int parkingTime = calcTimeDiff(inTime, "23:59");
            carParkingTime.put(carNum, carParkingTime.getOrDefault(carNum, 0) + parkingTime);
        }
        
        // 정답 list 만들기
        List<Integer> ansList = new ArrayList<>();
        
        while (!carParkingTime.isEmpty()) {     // 차량 번호 작은 자동차부터 주차 요금 계산
            ansList.add(calcCost(fees, carParkingTime.pollFirstEntry().getValue()));
        }
        
        // list -> 배열
        int[] answer = new int[ansList.size()];
        
        for (int i = 0; i < ansList.size(); i++) {
            answer[i] = ansList.get(i);
        }
        
        return answer;
    }
    
    /** 입차 출차 시간 차이 계산 */
    private int calcTimeDiff(String in, String out) {
        StringTokenizer st = new StringTokenizer(in, ":");
        int inH = Integer.parseInt(st.nextToken());     // 입차 시
        int inM = Integer.parseInt(st.nextToken());     // 입차 분
        
        st = new StringTokenizer(out, ":");
        int outH = Integer.parseInt(st.nextToken());    // 출차 시
        int outM = Integer.parseInt(st.nextToken());    // 출차 분
        
        int timeDiff = 0;
        
        if (outM >= inM) {
            timeDiff += (outM - inM) + (outH - inH) * 60;
        } else {
            timeDiff += (outM + 60 - inM) + (outH - 1 - inH) * 60;
        }
        
        return timeDiff;
    }
    
    /** 주차 요금 계산 */
    private int calcCost(int[] fees, int time) {
        int cost = fees[1]; // 기본 요금
        
        if (time > fees[0]) {   // 기본 시간 초과하면
            cost += Math.ceil((float)(time - fees[0]) / fees[2]) * fees[3];
        }
        
        return cost;
    }
}