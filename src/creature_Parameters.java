import java.util.Arrays;
import java.util.Random;

public abstract class creature_Parameters {
    protected int Attack;
    protected int Defense;
    protected int Health;
    protected int[] Damage;
    protected Random random = new Random();

    public creature_Parameters(int Attack, int Defense, int Health, int[] Damage) {
        setAttack(Attack);
        setDefense(Defense);
        setHealth(Health);
        setDamage(Damage);
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int Attack) {
        if (Attack < 1 || Attack > 30) {
            throw new IllegalArgumentException("Атака должна быть в диапазоне от 1 до 30");
        }
        this.Attack = Attack;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int Defense) {
        if (Defense < 1 || Defense > 30) {
            throw new IllegalArgumentException("Защита должна быть в диапазоне от 1 до 30");
        }
        this.Defense = Defense;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int Health) {
        if (Health < 0) {
            this.Health = 0;
        } else {
            this.Health = Health;
        }
    }

    public int[] getDamage() {
        return Damage;
    }

    public void setDamage(int[] Damage) {
        if (Damage == null || Damage.length == 0) {
            throw new IllegalArgumentException("Массив урона должен содержать хотя бы одно значение");
        }
        for (int damageValue : Damage) {
            if (damageValue <= 0) {
                throw new IllegalArgumentException("Значения урона должны быть больше 0");
            }
        }
        this.Damage = Arrays.copyOf(Damage, Damage.length);
    }

    public boolean isAlive() {
        return Health > 0;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Урон не может быть отрицательным");
        }
        Health -= damage;
        if (Health < 0) {
            Health = 0;
        }
    }

    public abstract void death();

    public abstract void attack(creature_Parameters target);
}
