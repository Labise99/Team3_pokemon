package pocketmon;

import lombok.Getter;

@Getter
public class LegendPokemon extends Pokemon {
    public LegendPokemon(String pokemonName, int HP, int level) {
        super(pokemonName, HP, level);
    }
}
