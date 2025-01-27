package pocketmon;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Pokemon implements IPokemon {
    private String pokemonName;

    @Setter
    private int HP;
    private int level;

    // 타입 확인 메서드
    @Getter
    private List<String> types; // 포켓몬 타입 목록

    public Pokemon(String pokemonName, int HP, int level) {
        this.pokemonName = pokemonName;
        this.HP = HP;
        this.level = level;
        this.types = new ArrayList<>(); // 타입 목록 초기화
    }

    // 특정 상황에서 랜덤 타입 추가 메서드
    public void typeAdd() {
        // 타입 목록
        List<String> availableTypes = List.of("WATER", "FIRE", "EARTH", "SKY", "LEGENDARY", "MYSTIC", "NORMAL", "ELECTRIC");

        // 랜덤 타입 선택
        String randomType = availableTypes.get((int) (Math.random() * availableTypes.size()));

        // 타입 추가
        if (!types.contains(randomType)) {
            types.add(randomType);
            System.out.println(this.pokemonName + "에 새로운 타입이 추가되었습니다: " + randomType);
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
        System.out.println(this.pokemonName + "이(가) 진화합니다!");
        return new EvolvedPokemon(this.pokemonName + " 진화형", this.HP + 20, this.level + 1);
    }

    @Override
    public String toString() {
        return this.pokemonName + " (HP: " + this.HP + ", Level: " + this.level + ", Types: " + this.types + ")";
    }
}
