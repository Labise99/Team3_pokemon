package pocketmon;

import java.util.Map;
import java.util.Scanner;

public class CityMap {
    // 지도 및 위치 이동 관련 클래스
    // 객체화하지 않고 static으로 선언
    // Scanner 객체 생성
    Scanner inputReader = new Scanner(System.in);

    // 도시 선언
    static City palletTown = new City("태초마을"); // ?? : 태초마을이야!
    static City viridianCity = new City("상록시티");
    static City moonHill = new City("달맞이동산");

    // 도시 연결
    static {
        palletTown.connectCity(viridianCity);
        viridianCity.connectCity(palletTown);
    }


    // 도시 목록
    static Map<String, City> cityMap = Map.of(
            "태초마을", palletTown,
            "상록시티", viridianCity,
            "달맞이동산", moonHill
    );

    // 도시 이동 메소드
    public void walkCity(Trainer trainer) {
        // 트레이너의 현재 위치 불러오기
        City currentCity = cityMap.get(trainer.getCurrentCity());
        // 이동 가능한 도시 목록 출력
        System.out.println("이동 가능한 도시 목록:");
        for (City connectedCity : currentCity.getConnectedCities()) {
            System.out.println("- " + connectedCity.getCityName());
        }
        // 이동할 도시 선택
        System.out.print("이동할 도시를 선택하세요: ");
        String selectedCity = inputReader.nextLine();
        // 선택한 도시로 이동
        if (currentCity.getConnectedCities().contains(cityMap.get(selectedCity))) {
            trainer.setCurrentCity(selectedCity);
            System.out.println(selectedCity + "(으)로 이동하였습니다.");
        } else if(cityMap.containsKey(selectedCity)) {
            System.out.println("이동할 수 없는 도시입니다.");
        } else {
            System.out.println("존재하지 않는 도시입니다.");
        }
    }
}
