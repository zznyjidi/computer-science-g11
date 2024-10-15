
public class Pokemon {
    String name;
    int maxHP, currentHP;
    Attack[] attacks;

    public Pokemon(String name, int maxHP, Attack[] attacks) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attacks = attacks;
    }

    String getStatus() {
        return String.format("%s's HP: %" + (15 - name.length()) + "d", name, currentHP);
    }
}
