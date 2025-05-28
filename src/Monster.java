import java.util.Random;

public class Monster extends creature_Parameters {
    private Random random = new Random();
    private String name;
    private boolean isFemale;

    public Monster(String name, int Attack, int Defense, int Health, int[] Damage, boolean isFemale) {
        super(Attack, Defense, Health, Damage);
        this.name = name;
        this.isFemale = isFemale;
    }

    public String getName() {
        return name;
    }

    public boolean isFemale() {
        return isFemale;
    }

    @Override
    public void death() {
        if (Health <= 0) {
            System.out.println(name + " убит" + (isFemale ? "а" : ""));
        }
    }

    @Override
    public void attack(creature_Parameters target) {
        if (!isAlive()) {
            System.out.println("Нельзя атаковать, " + name + " мертв" + (isFemale ? "а" : "") + "!");
            return;
        }
        if (!target.isAlive()) {
            System.out.println("Нельзя атаковать, цель мертва!");
            return;
        }

        int diceRoll = random.nextInt(6) + 1;
        System.out.println(name + " бросил" + (isFemale ? "а" : "") + " кубик и выпало: " + diceRoll);

        if (diceRoll >= 5) {
            int damage = calculateDamage();
            target.takeDamage(damage);
            System.out.println(name + " нанес" + (isFemale ? "ла" : "") + " " + damage + " урона.");
        } else {
            System.out.println(name + " промахнул" + (isFemale ? "ась" : "ся") + "!");
        }
    }

    private int calculateDamage() {
        int randomIndex = random.nextInt(Damage.length);
        return Damage[randomIndex];
    }
}