public class Candy {
    String name;
    int sugarGrams;
    String texture;

    // Constructor
    public Candy(String name, int sugarGrams, String texture) {
        this.name = name;
        if (sugarGrams < 0) {
            System.err.println("sugarGrams can't be negative, set to 0. ");
            this.sugarGrams = 0;
        } else
            this.sugarGrams = sugarGrams;
        this.texture = texture;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSugarGrams() {
        return sugarGrams;
    }

    public void setSugarGrams(int sugarGrams) {
        if (sugarGrams < 0)
            System.err.println("sugarGrams can't be negative, not updating. ");
        else
            this.sugarGrams = sugarGrams;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    // toString Method
    @Override
    public String toString() {
        return "Candy [name=" + name + ", sugarGrams=" + sugarGrams + ", texture=" + texture + "]";
    }

    // Reduce sugarGrams by 5g
    public void lessSugarGrams() {
        if (this.sugarGrams < 5) {
            System.out.println("The Candy Contains less than 5g of sugar, reduced to 0g. ");
            this.sugarGrams = 0;
        } else
            this.sugarGrams -= 5;
    }
}
