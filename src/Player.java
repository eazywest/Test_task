public class Player extends creature_Parameters {
    private int healCount = 0;
    private final int maxHeals = 4;
    private final int maxHealth;

    public Player(int Attack, int Defense, int Health, int[] Damage) {
        super(Attack, Defense, Health, Damage);
        this.maxHealth = Health;
    }

    @Override
    public void death() {
        if (Health <= 0) {
            System.out.println("Игрок погиб");
        }
    }

    public void heal() {
        if (healCount < maxHeals) {
            double healAmount = 0.3 * maxHealth;
            Health = Math.min(maxHealth, Health + (int)healAmount);
            healCount++;
            System.out.println("Игрок вылечился на " + (int)healAmount + ". Текущее здоровье: " + Health);
        } else {
            System.out.println("Игрок использовал все попытки лечения!");
        }
    }

    @Override
    public void attack(creature_Parameters target) {
        if (!isAlive()) {
            System.out.println("Нельзя атаковать, игрок мертв!");
            return;
        }
        if (!target.isAlive()) {
            System.out.println("Нельзя атаковать, цель мертва!");
            return;
        }

        attack_Algorithm attackAlgorithm = new attack_Algorithm(this, target);
        if (attackAlgorithm.rollDice()) {
            int damage = calculateDamage();
            target.takeDamage(damage);
            System.out.println("Игрок нанес " + damage + " урона.");
        } else {
            System.out.println("Игрок промахнулся!");
        }
    }

    private int calculateDamage() {
        int randomIndex = random.nextInt(Damage.length);
        return Damage[randomIndex];
    }


    public int getHealCount() {
        return healCount;
    }

    public int getMaxHeals() {
        return maxHeals;
    }

}
