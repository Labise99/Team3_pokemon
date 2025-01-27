package pocketmon;

import java.util.Map;

public class CityMap {
    // 지도 및 위치 이동 관련 클래스
    // 객체화하지 않고 static으로 선언

    // 도시 선언
    static City palletTown = new City("태초마을"); // ?? : 태초마을이야!
    static City viridianCity = new City("상록시티");
    static City moonHill = new City("달맞이동산");

    // 도시 연결
    static {
        palletTown.connectCity(viridianCity);
        viridianCity.connectCity(palletTown);
        viridianCity.connectCity(moonHill);
        moonHill.connectCity(viridianCity);
    }


    // 도시 목록
    static Map<String, City> cityMap = Map.of(
            "태초마을", palletTown,
            "상록시티", viridianCity,
            "달맞이동산", moonHill
    );

    // 도시 이동 메소드
    public void moveCity(Trainer trainer) {
        // 현재 트레이너의 위치 정보 확인
        trainer.getCurrentCity();
        // 이동 가능한 도시 목록 출력
        cityMap.get(trainer.getCurrentCity()).getConnectedCities();
        // 이동할 도시 선택
        // 선택한 도시로 이동
    }
}
