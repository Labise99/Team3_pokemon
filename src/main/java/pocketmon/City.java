package pocketmon;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class City {
    // 도시 정보 관련 클래스
    private String cityName; // 도시 이름
    private List<City> connectedCities = new ArrayList<>(); // 연결된 도시 목록

    // 생성자
    public City(String cityName) {
        this.cityName = cityName;
    }

    // 도시 연결 메서드
    public void connectCity(City city) {
        connectedCities.add(city);
    }

    // 그럼 이제 무슨 메서드가 필요할 것인가
    // lombok을 통해 getter 생성, 도시 이름과 이동 가능한 도시는 해당 getter로 접근 가능
    // 더미 데이터로는 태초마을과 달맞이동산만 있으면 충분할 것
}
