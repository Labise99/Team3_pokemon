package pocketmon;

import lombok.Getter;

@Getter
public class SurfPokemon extends Pokemon implements ISurfable {
    public SurfPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
    }

    @Override
    public void surf(PokeTown tgPokeTown, Trainer trainer) {
        trainer.setCurrentPokeTown(tgPokeTown);
        System.out.println(this.getPokemonName() + "(이)가 " + tgPokeTown.getCityName() + "(으)로 서핑합니다!");
    }

    @Override
    public void crossOcean(PokeTown tgPokeTown, Trainer trainer) {
        surf(tgPokeTown, trainer);
    }
}
