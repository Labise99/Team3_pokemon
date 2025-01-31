package pocketmon;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.*;

@Getter
public class Trainer implements ITrainer {
    private List<Pokemon> capturedPokemonList = new ArrayList<>();
    private Map<String, Pokemon> capturedPokemonByName = new HashMap<>();
    private PokeTown currentPokeTown = TownMap.townMap.get("태초마을"); // 현재 위치
    // 테스트를 위해 기본 위치를 태초마을로 지정
    // 추후 트레이너 객체 생성 시 위치를 따로 지정할 수 있도록 수정해야함

    public String name;
    private Scanner inputReader = new Scanner(System.in);

    // 현재 위치 getter
    public PokeTown getCurrentPokeTown() {
        return currentPokeTown;
    }

    // 현재 위치 setter
    public void setCurrentPokeTown(PokeTown currentPokeTown) {
        this.currentPokeTown = currentPokeTown;
    }

    // 도시 직접 이동 메소드
    public void walkCity() {
        // 트레이너의 현재 위치 불러오기
        currentPokeTown = TownMap.townMap.get(this.currentPokeTown);
        // 이동 가능한 도시 목록 출력
        System.out.println("이동 가능한 도시 목록:");
        currentPokeTown.getConnectedCities().forEach(city -> System.out.println("- " + city.getTownName()));
        // 이동할 도시 선택
        System.out.println("이동할 도시를 선택하세요:");
        String targetCity = inputReader.nextLine().trim();
        // 존재하는 도시인지 확인
        if (!TownMap.townMap.containsKey(targetCity)) {
            System.out.println("해당 도시가 존재하지 않습니다.");
            return;
        }
        // 이동 가능한 도시인지 확인
        if (!currentPokeTown.getConnectedCities().contains(TownMap.townMap.get(targetCity))) {
            System.out.println("해당 도시로 이동할 수 없습니다.");
            return;
        }
        // 선택한 도시로 이동
        this.currentPokeTown = TownMap.townMap.get(targetCity);
        System.out.println(targetCity + "(으)로 이동했습니다.");
    }

    // 트레이너 생성자: 초기 포켓몬 제공
    public Trainer(String name) {
        this.name = name;
        Pokemon starterPokemon = new Pokemon("꼬부기", 50, 5);
        capturedPokemonList.add(starterPokemon);
        capturedPokemonByName.put(starterPokemon.getPokemonName(), starterPokemon);
        System.out.println("초기 포켓몬으로 " + starterPokemon.getPokemonName() + "(이)가 제공되었습니다!");
    }

    @Override
    public Pokemon encounterWildPokemon() {
        List<Pokemon> wildPokemons = List.of(
                new FlyPokemon("구구", 30, 3),
                new FlyPokemon("피죤", 70, 10),
                new SurfPokemon("잉어킹", 25, 2),
                new LegendPokemon("루기아", 120, 20),
                new MysticPokemon("뮤츠", 150, 30),
                new SurfPokemon("꼬부기", 20,3),
                new LunaPokemon("푸린", 30, 20),
                new LunaPokemon("삐삐", 40, 20));
        Random random = new Random();
        return wildPokemons.get(random.nextInt(wildPokemons.size()));
    }

    public void arriveAtMoonHill() {
        System.out.println("트레이너가 달맞이 동산에 도착했습니다!");

        boolean hasLunaPokemon = false;

        Iterator<Pokemon> iterator = capturedPokemonList.iterator();
        List<EvolvedPokemon> evolvedPokemons = new ArrayList<>();

        while (iterator.hasNext()) {
            Pokemon pokemon = iterator.next();
            if (pokemon instanceof LunaPokemon) {
                hasLunaPokemon = true;
                LunaPokemon lunaPokemon = (LunaPokemon) pokemon;
                if (!lunaPokemon.isEvolved()) {
                    // 진화 실행
                    EvolvedPokemon evolvedPokemon = lunaPokemon.evolve();
                    iterator.remove(); // 안전하게 현재 항목 제거
                    evolvedPokemons.add(evolvedPokemon); // 나중에 추가할 항목 저장
                }
            }
        }

        // 진화된 포켓몬 추가
        capturedPokemonList.addAll(evolvedPokemons);

        if (!hasLunaPokemon) {
            System.out.println("트레이너는 진화 가능한 푸린이나 삐삐를 소유하고 있지 않습니다.");
        }
    }


    public void showSpecialAbilityPokemon() {
        System.out.println("=== 특수 능력을 가진 포켓몬 목록 ===");
        boolean hasSpecialPokemon = capturedPokemonList.stream()
                .filter(pokemon -> pokemon instanceof FlyPokemon || pokemon instanceof SurfPokemon)
                .peek(pokemon -> {
                    String type = pokemon instanceof FlyPokemon ? "비행" : "물";
                    System.out.println("- " + pokemon.getPokemonName() + " (Type: " + type + ", Level: " + pokemon.getLevel() + ", HP: " + pokemon.getHP() + ")");
                })
                .count() > 0;

        if (!hasSpecialPokemon) {
            System.out.println("특수 능력을 가진 포켓몬이 없습니다.");
        }
    }

    public void useSpecialAbility(String pokemonName) {
        // 소유한 포켓몬 중 이름으로 찾기
        Pokemon pokemon = capturedPokemonByName.get(pokemonName);
        // 해당 포켓몬이 없으면 메시지 출력 후 종료
        if (pokemon == null) {
            System.out.println("해당 포켓몬은 트레이너가 소유하고 있지 않습니다.");
            return;
        }
        if (!(pokemon instanceof FlyPokemon || pokemon instanceof SurfPokemon)) {
            System.out.println("해당 포켓몬은 특수 능력을 사용할 수 없습니다.");
            return;
        }
        // 목표 도시 입력
        System.out.println("이동할 도시를 입력하세요:");
        String tgCityName = inputReader.nextLine().trim();
        // 목표 도시가 존재하는지 확인
        if (!TownMap.townMap.containsKey(tgCityName)) {
            System.out.println("해당 도시가 존재하지 않습니다.");
            return;
        }
        PokeTown targetPokeTown = TownMap.townMap.get(tgCityName);

        // FlyPokemon이면 fly 메소드 호출, SurfPokemon이면 surf 메소드 호출
        if (pokemon instanceof FlyPokemon) {
            ((FlyPokemon) pokemon).fly(targetPokeTown, this);
        }
        if (pokemon instanceof SurfPokemon) {
            ((SurfPokemon) pokemon).surf(targetPokeTown, this);
        }

        // 이동 지역이 달맞이동산이라면 arriveAtMoonHill 메소드 호출
        if(this.currentPokeTown.getTownName().equals("달맞이동산")){
            arriveAtMoonHill();
        }
    }

    public void showOwnedPokemon() {
        if (capturedPokemonList.isEmpty()) {
            System.out.println("현재 소유한 포켓몬이 없습니다.");
        } else {
            System.out.println("=== " + name + "의 포켓몬 목록 ===");
            capturedPokemonList.forEach(pokemon -> System.out.println("- " + pokemon));
        }
    }

    public void tradePokemon(Trainer otherTrainer, String tgPokemonName, String myPokemonName) {
        // 내 포켓몬과 상대 포켓몬 검색
        Pokemon myPokemon = capturedPokemonByName.get(myPokemonName);
        Pokemon theirPokemon = otherTrainer.capturedPokemonByName.get(tgPokemonName);

        if (myPokemon == null) {
            System.out.println("교환 실패: 당신은 " + myPokemonName + "을(를) 가지고 있지 않습니다.");
            return;
        }
        if (theirPokemon == null) {
            System.out.println("교환 실패: 상대는 " + tgPokemonName + "을(를) 가지고 있지 않습니다.");
            return;
        }

        // 트레이드 진행
        System.out.println("트레이딩을 시작합니다! --- " + myPokemonName + " <-> " + tgPokemonName + " ---");

        // 상대 트레이너의 리스트와 맵에서 제거
        otherTrainer.capturedPokemonList.remove(theirPokemon);
        otherTrainer.capturedPokemonByName.remove(tgPokemonName);

        // 내 리스트와 맵에서 제거
        capturedPokemonList.remove(myPokemon);
        capturedPokemonByName.remove(myPokemonName);

        // 서로의 리스트와 맵에 추가
        capturedPokemonList.add(theirPokemon);
        capturedPokemonByName.put(tgPokemonName, theirPokemon);

        otherTrainer.capturedPokemonList.add(myPokemon);
        otherTrainer.capturedPokemonByName.put(myPokemonName, myPokemon);

        // 교환 후 효과 (예: 타입 변경)
        theirPokemon.typeAdd();
        myPokemon.typeAdd();

        System.out.println("교환 성공: " + myPokemonName + "과 " + tgPokemonName + "이(가) 교환되었습니다.");
    }


    @Override
    public Map<String, Pokemon> searchDex(PokeDex.PokeCategory category) {
        Map<String, Pokemon> categoryResults = PokeDex.searchPokemon(category);
        if (categoryResults != null && !categoryResults.isEmpty()) {
            System.out.println("카테고리: " + category + "에 속하는 포켓몬:");
            categoryResults.forEach((key, value) -> System.out.println("이름: " + value.getPokemonName() + ", HP: " + value.getHP() + ", Level: " + value.getLevel()));
        } else {
            System.out.println("해당 카테고리에 속하는 포켓몬이 없습니다.");
        }
        return categoryResults;
    }

    public void explorePokeDex() {
        System.out.println("도감에서 검색할 방식을 선택하세요:");
        System.out.println("1: 이름 검색");
        System.out.println("2: 카테고리 검색");
        System.out.println("3: 전체 보기");

        String choice = inputReader.nextLine().trim();

        switch (choice) {
            case "1":
                System.out.println("검색할 포켓몬 이름을 입력하세요:");
                String name = inputReader.nextLine().trim();
                Pokemon result = PokeDex.searchPokemon(name);
                if (result != null) {
                    System.out.println("도감에서 찾은 포켓몬: " + result.getPokemonName() + ", HP: " + result.getHP() + ", Level: " + result.getLevel());
                } else {
                    System.out.println("해당 이름의 포켓몬이 도감에 없습니다.");
                }
                break;

            case "2":
                System.out.println("검색할 카테고리를 선택하세요:");
                for (PokeDex.PokeCategory category : PokeDex.PokeCategory.values()) {
                    System.out.println("- " + category);
                }
                String categoryInput = inputReader.nextLine().trim().toUpperCase();
                try {
                    PokeDex.PokeCategory category = PokeDex.PokeCategory.valueOf(categoryInput);
                    searchDex(category);
                } catch (IllegalArgumentException e) {
                    System.out.println("잘못된 카테고리 입력입니다.");
                }
                break;

            case "3":
                System.out.println("도감 전체 목록:");
                Map<String, Pokemon> allPokemon = PokeDex.getAllPokemon();
                allPokemon.values().stream()
                        .sorted(Comparator.comparingInt(Pokemon::getLevel))
                        .forEach(p -> System.out.println("이름: " + p.getPokemonName() + ", HP: " + p.getHP() + ", Level: " + p.getLevel()));
                break;

            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    @Override
    public void hunt(Pokemon wildPokemon) {
        System.out.println("야생 포켓몬 " + wildPokemon.getPokemonName() + "(이)가 나타났습니다!");
        while (wildPokemon.getHP() > 0) {
            System.out.println("1: 공격 / 2: 포획 / 3: 도망");
            String input = inputReader.nextLine().trim();
            if (!input.matches("[123]")) {
                System.out.println("잘못된 입력입니다. 1, 2, 3 중에서 선택하세요.");
                continue;
            }

            int choice = Integer.parseInt(input);
            if (choice == 1) {
                attackWildPokemon(wildPokemon);
                if (wildPokemon.getHP() == 0) {
                    System.out.println("야생 " + wildPokemon.getPokemonName() + "(이)가 기절했습니다.");
                    break;
                }
            } else if (choice == 2) {
                Pokemon capturedPokemon = capture(wildPokemon);
                if (capturedPokemon != null) {
                    System.out.println(capturedPokemon.getPokemonName() + "(을)를 포획했습니다!");
                    break;
                } else {
                    System.out.println("포획에 실패했습니다!");
                }
            } else if (choice == 3) {
                System.out.println("트레이너가 도망쳤습니다.");
                break;
            }
        }
        System.out.println("전투 종료. 남은 포켓몬 상태 요약:");
        capturedPokemonList.forEach(p -> System.out.println(p.getPokemonName() + " - HP: " + p.getHP()));
    }
    public void attackWildPokemon(Pokemon wildPokemon) {
        Pokemon myPokemon = capturedPokemonList.get(0); // 첫 번째 포켓몬 사용

        // 데미지 계산
        Random random = new Random();
        int damageToWild = random.nextInt(10) + 5; // 5~15 데미지
        int damageToTrainer = random.nextInt(8) + 3; // 3~10 데미지

        wildPokemon.setHP(Math.max(0, wildPokemon.getHP() - damageToWild));
        myPokemon.setHP(Math.max(0, myPokemon.getHP() - damageToTrainer));

        // 전투 상태 출력
        System.out.println("전투 정보: " + myPokemon.getPokemonName() + " → " + damageToWild + " 데미지 입힘. 야생 포켓몬 남은 HP: " + wildPokemon.getHP());
        System.out.println("전투 정보: " + wildPokemon.getPokemonName() + " → " + damageToTrainer + " 데미지 입음. 내 포켓몬 남은 HP: " + myPokemon.getHP());

        if (myPokemon.getHP() == 0) {
            System.out.println(myPokemon.getPokemonName() + "(이)가 기절했습니다. 다른 포켓몬을 사용하세요!");
        }
    }

    @Override
    public Pokemon capture(Pokemon wildPokemon) {
        if (wildPokemon.getHP() == 0) {
            System.out.println("기절한 포켓몬은 포획할 수 없습니다.");
            return null;
        }

        Random random = new Random();
        int successChance = 50 + (wildPokemon.getHP() < 20 ? 30 : 0) - wildPokemon.getLevel();
        successChance = Math.max(10, Math.min(90, successChance));
        System.out.println("포획 성공 확률: " + successChance + "%");

        if (random.nextInt(100) < successChance) {
            capturedPokemonList.add(wildPokemon);
            capturedPokemonByName.put(wildPokemon.getPokemonName(), wildPokemon);
            return wildPokemon;
        }
        System.out.println("포획에 실패했습니다!");
        return null;
    }

    @Override
    public void battle(ITrainer enemyTrainer) {
        System.out.println("트레이너 간의 전투는 아직 구현되지 않았습니다.");
    }

    @Override
    public Pokemon searchDex(String pokemonName) {
        Pokemon result = PokeDex.searchPokemon(pokemonName);
        if (result != null) {
            System.out.println("도감에서 찾은 포켓몬: " + result.getPokemonName() + ", HP: " + result.getHP() + ", Level: " + result.getLevel());
        } else {
            System.out.println("해당 이름의 포켓몬이 도감에 없습니다.");
        }
        return result;
    }

    // 리스트에서 포켓몬 검색 메소드 추가
    public static Pokemon findPokemonByName(List<Pokemon> PokemonList, String PokemonName) {
        for (Pokemon pokemon : PokemonList) {
            if (pokemon.getPokemonName().equals(PokemonName)) {
                return pokemon;
            }
        }
        return null;
    }

    // 트레이딩
    @SneakyThrows
    public static void TradePokemon(Trainer trainer1, Trainer trainer2) {
        List<Pokemon> trainer1List = trainer1.getCapturedPokemonList();
        List<Pokemon> trainer2List = trainer2.getCapturedPokemonList();
        Scanner tradeInput = new Scanner(System.in);

        // 교환할 포켓몬이 없다면 리턴
        if (trainer1List.isEmpty()) {
            System.out.println("현재 소유한 포켓몬이 없습니다.");
            return;
        }

        // 현재 포켓몬 리스트
        // trainer.getName()
        System.out.println("=== " + trainer1 + "의 포켓몬 목록 ===");
        trainer1List.forEach(pokemon -> System.out.println("- " + pokemon.getPokemonName()));
               // + " (HP: " + pokemon.getHP() + ", Level: " + pokemon.getLevel() + ")"));
        System.out.println("=== " + trainer2 + "의 포켓몬 목록 ===");
        trainer2List.forEach(pokemon -> System.out.println("- " + pokemon.getPokemonName()));
                // + " (HP: " + pokemon.getHP() + ", Level: " + pokemon.getLevel() + ")"));

        // 포켓몬 지정 - 이름 검색 -> 리스트에 해당 이름 포켓몬이 없다면 교환 실패
        // 트레이너1이 선택할 포켓몬 이름 입력받기
        System.out.print("교환하고 싶은 나의 포켓몬의 이름을 입력하세요: ");
        String trainer1PokemonName = tradeInput.nextLine();

        // 트레이너2가 교환할 포켓몬 이름 입력받기
        System.out.print("교환하고 싶은 상대 포켓몬의 이름을 입력하세요: ");
        String trainer2PokemonName = tradeInput.nextLine();

        // 트레이너1과 트레이너2가 선택한 포켓몬을 이름으로 검색
        Pokemon pokemon1 = findPokemonByName(trainer1List, trainer1PokemonName);
        Pokemon pokemon2 = findPokemonByName(trainer2List, trainer2PokemonName);

        // 포켓몬이 없다면 교환 실패 메시지 출력
        if (pokemon1 == null) {
            System.out.println("현재 " + trainer1PokemonName + "을(를) 소유하지 않습니다. 교환 실패!");
            // 다시 선택하게 할 것인지?
            return;
        }
        if (pokemon2 == null) {
            System.out.println("상대는 " + trainer2PokemonName + "을(를) 소유하지 않습니다. 교환 실패!");
            return;
        }

        // 트레이딩 시작
        System.out.print("트레이딩을 시작합니다! ");
        System.out.println(" --- " + pokemon1 + " <-> " + pokemon2 + " --- ");
        Thread.sleep(1000);  // 1초 대기
        System.out.print("3... ");
        Thread.sleep(1000);
        System.out.print("2... ");
        Thread.sleep(1000);
        System.out.println("1... ☆");

        trainer1List.remove(pokemon1);
        trainer2List.remove(pokemon2);
        trainer1List.add(pokemon1);
        trainer2List.add(pokemon2);

        // 트레이딩 완료 후 리스트 업데이트
        System.out.println("축하합니다! 트레이딩이 완료되었습니다! \n");

        // TODO: 트레이드 효과로 진화 및 속성 변화 구현!
        ((ChangeCategoryPokemon) pokemon1).specialEvent(String.valueOf(pokemon1));
        ((ChangeCategoryPokemon) pokemon2).specialEvent(String.valueOf(pokemon2));

        System.out.println("=== " + trainer1 + "의 포켓몬 목록 ===");
        trainer1List.forEach(pokemon -> System.out.println("- " + pokemon.getPokemonName()));
        System.out.println("=== " + trainer2 + "의 포켓몬 목록 ===");
        trainer2List.forEach(pokemon -> System.out.println("- " + pokemon.getPokemonName()));
    }
}
