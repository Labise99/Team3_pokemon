package pocketmon;

import lombok.Getter;

@Getter
public class SurfPokemon extends Pokemon implements ISurfable {
    public SurfPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
    }

    @Override
    public void surf(String tgCity) {
        System.out.println(this.getPokemonName() + "(이)가 " + tgCity + "로 서핑합니다!");
    }

    @Override
    public void crossOcean(String tgCity) {
        surf(tgCity);
    }
}
