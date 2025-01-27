package pocketmon;

import lombok.Getter;

@Getter
public class FlyPokemon extends Pokemon implements IFlyable {
    public FlyPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
    }

    @Override
    public void fly(City tgCity, Trainer trainer) {
        trainer.setCurrentCity(tgCity);
        System.out.println(this.getPokemonName() + "(이)가 " + tgCity + "로 날아갑니다!");
    }

    @Override
    public void crossOcean(City tgCity, Trainer trainer) {
        fly(tgCity, trainer);
    }
}
