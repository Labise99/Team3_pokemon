package pocketmon;

import lombok.Getter;

@Getter
public class FlyPokemon extends Pokemon implements IFlyable {
    public FlyPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
    }

    @Override
    public void fly(PokeTown tgPokeTown, Trainer trainer) {
        trainer.setCurrentPokeTown(tgPokeTown);
        System.out.println(this.getPokemonName() + "(이)가 " + tgPokeTown.getCityName() + "(으)로 날아갑니다!");
    }

    @Override
    public void crossOcean(PokeTown tgPokeTown, Trainer trainer) {
        fly(tgPokeTown, trainer);
    }
}
