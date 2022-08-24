import java.util.ArrayList;

public record Drink(ArrayList<Ingredient> ingredients, String name) {

    @Override
    public String toString() {
        return name + ingredients;
    }

}
