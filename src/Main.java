import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int choice;
        int round = 1;

        Player player = new Player(15, 10, 100, new int[]{1, 2, 3, 4, 5, 6});


        Monster gargoyle = new Monster("Горгулья", 10, 5, 50, new int[]{1, 2, 3, 4}, true);
        Monster chimera = new Monster("Химера", 12, 7, 60, new int[]{2, 3, 4, 5}, true);
        Monster dragon = new Monster("Дракон", 15, 9, 70, new int[]{3, 4, 5, 6}, false);

        Monster attackingMonster = null;

        System.out.println("Добро пожаловать игрок!");

        while (player.isAlive() && (gargoyle.isAlive() || chimera.isAlive() || dragon.isAlive())) {
            System.out.println("\n===== Раунд " + round + " =====");
            System.out.println("Здоровье игрока: " + player.getHealth());

            System.out.println("Выберите действие:");
            System.out.println("1 - Бросить кубик, чтобы атаковать Горгулью (" + (gargoyle.isAlive() ? "Жива" : "Мертва") + ")");
            System.out.println("2 - Бросить кубик, чтобы атаковать Химеру (" + (chimera.isAlive() ? "Жива" : "Мертва") + ")");
            System.out.println("3 - Бросить кубик, чтобы атаковать Дракона (" + (dragon.isAlive() ? "Жив" : "Мертв") + ")");
            System.out.println("4 - Вылечиться (" + player.getHealCount() + "/" + player.getMaxHeals() + " исцелений осталось)");
            System.out.println("5 - Выйти из игры");
            System.out.print("Ваш выбор: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    attackingMonster = gargoyle;
                    attackMonster(player, gargoyle, round, random);
                    break;
                case 2:
                    attackingMonster = chimera;
                    attackMonster(player, chimera, round, random);
                    break;
                case 3:
                    attackingMonster = dragon;
                    attackMonster(player, dragon, round, random);
                    break;
                case 4:
                    player.heal();
                    break;
                case 5:
                    System.out.println("Выход из игры.");
                    sc.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }


            if (attackingMonster != null && attackingMonster.isAlive()) {
                System.out.print("Раунд " + round + ". ");
                attackingMonster.attack(player);
            }

            System.out.println("Здоровье игрока: " + player.getHealth());

            if (!gargoyle.isAlive() && !chimera.isAlive() && !dragon.isAlive()) {
                System.out.println("\nПоздравляем! Вы победили всех монстров за " + round + " раундов!");
                break;
            }

            if (!player.isAlive()) {
                System.out.println("\nВы проиграли! Вы продержались " + round + " раундов.");
                break;
            }

            round++;
        }

        sc.close();
    }


    private static void attackMonster(Player player, Monster monster, int round, Random random) {
        if (monster.isAlive()) {
            String monsterName = monster.getName();
            String toAttack = "Вы бросили кубик, чтобы атаковать ";
            if (monsterName.equals("Горгулья")) {
                toAttack += "Горгулью";
            } else if (monsterName.equals("Химера")) {
                toAttack += "Химеру";
            } else {
                toAttack += "Дракона";
            }

            int diceRoll = random.nextInt(6) + 1;
            System.out.println(toAttack + " и выпало: " + diceRoll);

            if (diceRoll >= 5) {
                System.out.print("Раунд " + round + ". ");
                player.attack(monster);
                System.out.println("Атака успешна!");
            } else {
                System.out.println("Атака провалилась!");
            }
            System.out.println("Здоровье " + monsterName + ": " + monster.getHealth());
        } else {
            System.out.println(monster.getName() + " уже мертв" + (monster.isFemale() ? "а" : "") + "!");
        }
    }
}