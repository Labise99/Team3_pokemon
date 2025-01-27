package pocketmon;

import lombok.Getter;

import java.util.List;

@Getter
public class City {
    // 도시 정보 관련 클래스
    private String cityName; // 도시 이름
    private List<City> connectedCities; // 연결된 도시 목록
    private List<Trainer> trainers; // 해당 도시에 있는 트레이너 목록

    // 생성자
    public City(String cityName, List<City> connectedCities) {
        this.cityName = cityName;
        this.connectedCities = connectedCities;
    }

    // 그럼 이제 무슨 메서드가 필요할 것인가
    // lombok을 통해 getter 생성, 도시 이름과 이동 가능한 도시는 해당 getter로 접근 가능
    // 더미 데이터로는 태초마을과 달맞이동산만 있으면 충분할 것

    // 이동 메서드
    public void moveCity(Trainer trainer) {
        // 현재 트레이너의 위치 정보 확인
        // 이동 가능한 도시 목록 출력
        // 이동할 도시 선택
        // 선택한 도시로 이동
    }
}
