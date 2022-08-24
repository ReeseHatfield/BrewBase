public record Ingredient(String name) {

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
//overload constructor with part ratio and optional boolean