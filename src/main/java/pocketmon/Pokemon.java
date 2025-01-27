package pocketmon;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Pokemon implements IPokemon {
    private String pokemonName;

    @Setter
    private int HP;
    private int level;

    private List<String> types; // 타입 목록

    public Pokemon(String pokemonName, int HP, int level) {
        this.pokemonName = pokemonName;
        this.HP = HP;
        this.level = level;
        this.types = new ArrayList<>(); // 타입 초기화
    }

    // 타입 추가 메서드
    public void typeAdd() {
        // 타입 목록
        List<String> availableTypes = List.of("WATER", "FIRE", "EARTH", "SKY", "NORMAL", "ELECTRIC");

        // 랜덤 타입 선택
        String randomType = availableTypes.get(new Random().nextInt(availableTypes.size()));

        // 타입 추가
        if (!types.contains(randomType)) {
            types.add(randomType);
            System.out.println(this.pokemonName + "에 새로운 타입이 추가되었습니다: " + randomType);
        } else {
            System.out.println(this.pokemonName + "은(는) 이미 타입 " + randomType + "을(를) 가지고 있습니다.");
        }
    }

    @Override
    public void attack(Pokemon tgPokemon) {
        int damage = (int) (Math.random() * 10) + 5;
        tgPokemon.setHP(Math.max(0, tgPokemon.getHP() - damage));
        System.out.println(this.pokemonName + "이(가) " + tgPokemon.getPokemonName() + "에게 " + damage + "의 데미지를 입혔습니다!");
    }

    @Override
    public void flee(int enemyLv) {
        if (this.level < enemyLv) {
            System.out.println(this.pokemonName + "이(가) 도망쳤습니다.");
        } else {
            System.out.println(this.pokemonName + "이(가) 싸움을 계속합니다.");
        }
    }

    @Override
    public Pokemon evolve() {
        return null;
    }

    @Override
    public String toString() {
        return this.pokemonName + " (HP: " + this.HP + ", Level: " + this.level + ", Types: " + this.types + ")";
    }
}
