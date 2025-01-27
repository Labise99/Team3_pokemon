package pocketmon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        // 트레이너 더미 데이터 생성
        Map<Integer, Trainer> trainerList = new HashMap<>();
        Trainer trainer1 = new Trainer("한지우");
        Trainer trainer2 = new Trainer("오박사");
        Scanner scanner = new Scanner(System.in);

        // 트레이너 리스트 생성
        trainerList.put(1, trainer1);
        trainerList.put(2, trainer2);

        // 트레이너가 보유한 포켓몬 더미 데이터
        Pokemon pikachu = new Pokemon("피카츄", 50, 5); // 피카츄 생성
        trainer2.capturedPokemonList.add(pikachu); // 오박사 포켓몬 리스트에 추가
        trainer2.capturedPokemonByName.put(pikachu.getPokemonName(), pikachu); // 오박사 포켓몬 맵에 추가

        while (true) {
            System.out.println("\n==== 포켓몬 게임 ====");
            System.out.println("1: 전투 시작");
            System.out.println("2: 도감 검색");
            System.out.println("3: 포켓몬 특수 능력 사용");
            System.out.println("4: 현재 가진 포켓몬 보기");
            System.out.println("5: 포켓몬 교환하기");
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
                    // 포켓몬 교환 기능
                    System.out.println("\n교환 가능한 상대의 포켓몬:");
                    trainer2.showOwnedPokemon();
                    System.out.print("상대의 포켓몬 이름: ");
                    String tgPokemon = scanner.nextLine().trim();
                    System.out.print("내 포켓몬 이름: ");
                    String myPokemon = scanner.nextLine().trim();
                    trainer1.tradePokemon(trainer2, tgPokemon, myPokemon);
                    trainer1.showOwnedPokemon();
                    break;

                case "6":
                    // 종료
                    System.out.println("게임을 종료합니다. 감사합니다!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 1, 2, 3, 4, 5 중에서 선택하세요.");
            }
        }
    }
}
