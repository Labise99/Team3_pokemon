package pocketmon;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
public class ChangeCategoryPokemon extends Pokemon {
    private static Map<String, PokeDex.PokeCategory> pokemonCategoryMap = new HashMap<>();

    public ChangeCategoryPokemon(String pokemonName, int HP, int level) {
        super(pokemonName,HP,level);
    }

    public void specialEvent(String pokemonName) {
        changeCategory(pokemonName);
    }

    @SneakyThrows
    public void changeCategory(String pokemonName) {
        Random rand = new Random();
        PokeDex.PokeCategory newCategory = getRandomPokeCategory();
        PokeDex.PokeCategory currentCategory = pokemonCategoryMap.get(pokemonName);

        // 랜덤으로 나온 카테고리가 원래 카테고리와 같을 시 리턴
        if (currentCategory == newCategory) {
            return;
        }

        System.out.println(this.getPokemonName() + "의 속성이 " + currentCategory + "에서 " + newCategory + "로 변화합니다!");
        Thread.sleep(500);  // 0.5초 대기
        System.out.print("3... ");
        Thread.sleep(500);
        System.out.print("2... ");
        Thread.sleep(500);
        System.out.println("1... ☆");

        pokemonCategoryMap.put(getPokemonName(), newCategory);
        System.out.println("속성 변화가 완료되었습니다!");
    }

    // PokeCategory enum에서 랜덤으로 하나를 반환
    private PokeDex.PokeCategory getRandomPokeCategory() {
        Random random = new Random();
        PokeDex.PokeCategory[] categories = PokeDex.PokeCategory.values();
        int index = random.nextInt(categories.length);
        return categories[index];
    }

}
