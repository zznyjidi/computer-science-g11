
public class Attack {

    String name;
    int minDamage, maxDamage;
    int missRatePercent;

    public Attack(String name, int minDamage, int maxDamage, int missRatePercent) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.missRatePercent = missRatePercent;
    }
}
