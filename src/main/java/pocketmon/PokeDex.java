package pocketmon;

import java.util.HashMap;
import java.util.Map;

//TODO
//구현 기능
//메소드1
    //대상 트레이너 지정
    //상대 포켓몬 리스트 검색

//메소드2
    //교환 대상 포켓몬 지정
    //상대, 내 보유 포켓몬 리스트 업데이트

    //진화, 속성변화 메소드 호출
        // +@ : 교환 신청 수락

//교환시 특수이벤트???
    //진화 (기존 진화 메소드 재활용)
    //속성변화 (포켓몬 타입 변화: 랜덤으로 변화)


public class PokeDex {
    private static Map<String, Pokemon> pokemonByName = new HashMap<>();
    private static Map<PokeCategory, Map<String, Pokemon>> pokemonByCategory = new HashMap<>();

    public enum PokeCategory {
        WATER, FIRE, EARTH, SKY, LEGENDARY, MYSTIC, NORMAL, ELECTRIC, LUNA
    }

    /// TODO: 진화형
    static {
        // 카테고리 초기화
        for (PokeCategory category : PokeCategory.values()) {
            pokemonByCategory.put(category, new HashMap<>());
        }

        // 일반 포켓몬 데이터
        Pokemon squirtle = new Pokemon("꼬부기", 50, 5);
        Pokemon charmander = new Pokemon("파이리", 45, 5);
        Pokemon bulbasaur = new Pokemon("이상해씨", 55, 5);

        // 특수 포켓몬 데이터
        FlyPokemon pidgeot = new FlyPokemon("피죤", 70, 10);
        SurfPokemon magikarp = new SurfPokemon("잉어킹", 25, 2);
        LegendPokemon lugia = new LegendPokemon("루기아", 120, 70);
        MysticPokemon mewtwo = new MysticPokemon("뮤츠", 150, 70);
        EvolvedPokemon charizard = new EvolvedPokemon("리자몽", 100, 25);

        // 달맞이동산 진화 포켓몬
        LunaPokemon ppippi = new LunaPokemon("삐삐", 40, 20);
        LunaPokemon purin = new LunaPokemon("푸린", 30, 18);

        // 도감에 추가
        pokemonByName.put("꼬부기", squirtle);
        pokemonByName.put("파이리", charmander);
        pokemonByName.put("이상해씨", bulbasaur);
        pokemonByName.put("피죤", pidgeot);
        pokemonByName.put("잉어킹", magikarp);
        pokemonByName.put("루기아", lugia);
        pokemonByName.put("뮤츠", mewtwo);
        pokemonByName.put("리자몽", charizard);
        pokemonByName.put("삐삐", ppippi);
        pokemonByName.put("푸린", purin);

        // 카테고리에 추가
        pokemonByCategory.get(PokeCategory.WATER).put("꼬부기", squirtle);
        pokemonByCategory.get(PokeCategory.WATER).put("잉어킹", magikarp);
        pokemonByCategory.get(PokeCategory.FIRE).put("파이리", charmander);
        pokemonByCategory.get(PokeCategory.FIRE).put("리자몽", charizard);
        pokemonByCategory.get(PokeCategory.SKY).put("피죤", pidgeot);
        pokemonByCategory.get(PokeCategory.LEGENDARY).put("루기아", lugia);
        pokemonByCategory.get(PokeCategory.MYSTIC).put("뮤츠", mewtwo);
        pokemonByCategory.get(PokeCategory.EARTH).put("이상해씨", bulbasaur);
        pokemonByCategory.get(PokeCategory.LUNA).put("삐삐", ppippi);
        pokemonByCategory.get(PokeCategory.LUNA).put("푸린", purin);
    }

    public static Pokemon searchPokemon(String name) {
        return pokemonByName.get(name);
    }

    public static Map<String, Pokemon> searchPokemon(PokeCategory category) {
        return pokemonByCategory.get(category);
    }

    public static Map<String, Pokemon> getAllPokemon() {
        return pokemonByName;
    }
}
