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

    // fly, surf도 결국 위치 이동인데 여기서 케어를 해야 하나?
    // 그렇다고 하면 FlyPokemon, SurfPokemon의 존재의의가 없는데?
    // 해결 : walk는 트레이너가 하는 행동, fly랑 surf는 포켓몬이 하는 행동
    // 따라서 fly, surf는 각각 FlyPokemon, SurfPokemon 클래스에 구현, walk는 Trainer에 구현
    // CityMap은 도시와 지도 정보만 총괄!
}
