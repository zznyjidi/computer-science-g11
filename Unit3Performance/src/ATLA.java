/**
 * Avatar the Last Airbender Character
 * By Zixuan Zhao
 */
public class ATLA {

    // Fields
    String characterName;
    int age;
    String nation;

    // Constructor
    public ATLA(String characterName, int age, String nation) {
        this.characterName = characterName;
        setAge(age);
        setNation(nation);
    }

    // Getters and Setters
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0)
            this.age = age;
        else
            System.err.println("Age can't be negative! ");
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        if (nation.equals("Earth") || nation.equals("Fire") || nation.equals("Water") || nation.equals("Air"))
            this.nation = nation;
        else
            System.err.println("Nation must be Earth, Fire, Water or Air! ");
    }

    // toString
    @Override
    public String toString() {
        return "ATLA [First Name=" + characterName + ", Age=" + age + ", Nation=" + nation + "]";
    }
}
