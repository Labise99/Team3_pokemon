package pocketmon;

import lombok.Getter;

@Getter
public class SurfPokemon extends Pokemon implements ISurfable {
    public SurfPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
    }

    @Override
    public void surf(City tgCity, Trainer trainer) {
        trainer.setCurrentCity(tgCity);
        System.out.println(this.getPokemonName() + "(이)가 " + tgCity + "로 서핑합니다!");
    }

    @Override
    public void crossOcean(City tgCity, Trainer trainer) {
        surf(tgCity, trainer);
    }
}
