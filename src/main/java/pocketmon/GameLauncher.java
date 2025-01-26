package pocketmon;

import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Trainer trainer = new Trainer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== 포켓몬 게임 ====");
            System.out.println("1: 전투 시작");
            System.out.println("2: 도감 검색");
            System.out.println("3: 포켓몬 특수 능력 사용");
            System.out.println("4: 현재 가진 포켓몬 보기");
            System.out.println("5: 종료");
            System.out.print("원하는 기능을 선택하세요: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // 전투 기능
                    Pokemon wildPokemon = trainer.encounterWildPokemon();
                    trainer.hunt(wildPokemon);
                    break;

                case "2":
                    // 도감 검색 기능
                    trainer.explorePokeDex();
                    break;

                case "3":
                    // 특수 능력 사용
                    trainer.showSpecialAbilityPokemon();
                    System.out.println("특수 능력을 사용할 포켓몬 이름을 입력하세요:");
                    String specialPokemonName = scanner.nextLine().trim();
                    trainer.useSpecialAbility(specialPokemonName);
                    break;

                case "4":
                    // 현재 가진 포켓몬 보기
                    trainer.showOwnedPokemon();
                    break;

                case "5":
                    // 종료
                    System.out.println("게임을 종료합니다. 감사합니다!");
                    System.exit(0);

                default:
                    System.out.println("잘못된 입력입니다. 1, 2, 3, 4, 5 중에서 선택하세요.");
            }
        }
    }
}
