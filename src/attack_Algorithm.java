import java.util.Random;

public class attack_Algorithm {
    private creature_Parameters attacker;
    private creature_Parameters target;
    private Random random = new Random();

    public attack_Algorithm(creature_Parameters attacker, creature_Parameters target) {
        this.attacker = attacker;
        this.target = target;
    }

    public int attack_Modifier() {
        return attacker.Attack - target.Defense + 1;
    }

    public boolean rollDice() {
        int attackModifier = attack_Modifier();
        int numberOfRolls = Math.max(1, attackModifier);

        for (int i = 0; i < numberOfRolls; i++) {
            int roll = random.nextInt(6) + 1;
            if (roll >= 5) {
                return true;
            }
        }
        return false;
    }
}
