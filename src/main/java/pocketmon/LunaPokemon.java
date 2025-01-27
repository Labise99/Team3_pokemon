package pocketmon;

import lombok.Getter;

@Getter
public class LunaPokemon extends Pokemon {
    private boolean evolved; // 진화 여부

    public LunaPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
        this.evolved = false;
    }

    // 진화 메서드
    public EvolvedPokemon evolve() {
        if (!evolved) { // 진화 여부 확인
            this.evolved = true; // 진화 상태 업데이트
            System.out.println("어? 어라 포켓몬이 이상하다...");
            System.out.println(getPokemonName() + "이(가) 진화합니다!");
            return new EvolvedPokemon(getPokemonName() + " (진화형)", getHP() + 20, getLevel());
        } else {
            System.out.println(getPokemonName() + "은(는) 이미 진화했습니다!");
        }
        return null;
    }
}
