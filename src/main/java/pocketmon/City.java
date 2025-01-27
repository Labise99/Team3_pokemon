package pocketmon;

import java.util.List;

public class City {
    // 도시 정보 관련 클래스
    private String cityName; // 도시 이름
    private List<City> connectedCities; // 연결된 도시 목록

    // 생성자
    public City(String cityName, List<City> connectedCities) {
        this.cityName = cityName;
        this.connectedCities = connectedCities;
    }

    // 그럼 이제 무슨 메서드가 필요할 것인가
}
