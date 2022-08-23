import java.util.ArrayList;

public class Drink {
    private final ArrayList<Ingredient> ingredients;
    private final String name;

    public Drink(ArrayList<Ingredient> ingredients, String name) {
        this.ingredients = ingredients;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ingredients;
    }

}
