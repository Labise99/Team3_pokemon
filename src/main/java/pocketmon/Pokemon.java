package pocketmon;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Pokemon implements IPokemon {
    private String pokemonName;
    // 추가된 setHP 메서드
    @Setter
    private int HP;
    private int level;

    public Pokemon(String pokemonName, int HP, int level) {
        this.pokemonName = pokemonName;
        this.HP = HP;
        this.level = level;
    }

    @Override
    public void attack(Pokemon tgPokemon) {
        // 랜덤 데미지 계산
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

}
