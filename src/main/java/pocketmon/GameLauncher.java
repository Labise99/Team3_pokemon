package pocketmon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        Scanner scanner = new Scanner(System.in);

        Pokemon squirtle = new Pokemon("꼬부기", 50, 5);
        Pokemon charmander = new Pokemon("파이리", 45, 5);
        Pokemon bulbasaur = new Pokemon("이상해씨", 55, 5);

        List<Pokemon> trainer1List = new ArrayList<Pokemon>();
        trainer1List.add(squirtle);
        List<Pokemon> trainer2List = new ArrayList<Pokemon>();
        trainer2List.add(charmander);
        trainer2List.add(bulbasaur);

        while (true) {
            System.out.println("\n==== 포켓몬 게임 ====");
            System.out.println("1: 전투 시작");
            System.out.println("2: 도감 검색");
            System.out.println("3: 포켓몬 특수 능력 사용");
            System.out.println("4: 현재 가진 포켓몬 보기");
            System.out.println("5: 트레이드하기");
            System.out.println("6: 종료");
            System.out.print("원하는 기능을 선택하세요: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // 전투 기능
                    Pokemon wildPokemon = trainer1.encounterWildPokemon();
                    trainer1.hunt(wildPokemon);
                    break;

                case "2":
                    // 도감 검색 기능
                    trainer1.explorePokeDex();
                    break;

                case "3":
                    // 특수 능력 사용
                    trainer1.showSpecialAbilityPokemon();
                    System.out.println("특수 능력을 사용할 포켓몬 이름을 입력하세요:");
                    String specialPokemonName = scanner.nextLine().trim();
                    trainer1.useSpecialAbility(specialPokemonName);
                    break;

                case "4":
                    // 현재 가진 포켓몬 보기
                    trainer1.showOwnedPokemon();
                    break;

                case "5":
                    // 트레이드하기
                    Trainer.TradePokemon(trainer1, trainer2);

                case "6":
                    // 종료
                    System.out.println("게임을 종료합니다. 감사합니다!");
                    System.exit(0);

                default:
                    System.out.println("잘못된 입력입니다. 1, 2, 3, 4, 5 중에서 선택하세요.");
            }

        }
    }
}
