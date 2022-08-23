public class Ingredient {
    private final String name;
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }
}
//overload constructor with part ratio and optional boolean