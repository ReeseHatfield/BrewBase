
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BrewBase {
    private static ArrayList<Drink> drinks = new ArrayList<>();
    private static File persistText = new File("drinks");
    private ArrayList<Ingredient> allIngredients = initializeIngredients();

    public BrewBase(){
        //when constructed, read the persistent file and update the drink arr. accordingly
        this.playIntro();
        this.runGUI();
        try {
            Scanner scanner = new Scanner(persistText);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] splitArr = s.split("\\|");

                String name = splitArr[0];

                ArrayList<Ingredient> ingredients = new ArrayList<>();
                String ingString = splitArr[1];
                ingString = ingString.replace("[","");
                ingString = ingString.replace("]","");
                ingString = ingString.replace(",","");

                String[] ingredientArr = ingString.split(" ");
                for (String value : ingredientArr) {
                    ingredients.add(new Ingredient(value));
                }
                drinks.add(new Drink(ingredients,name+"|"));
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }



    public void addDrink(){
        Scanner input = new Scanner(System.in);

        System.out.println("What is the name of the drink you would like to add?");
        String name = input.nextLine() + "|";

        System.out.println("What ingredients would you like to add?");
        System.out.println("Type \"Finished\" when you are done adding ingredients");

        ArrayList<Ingredient> drinkIngredients = new ArrayList<>();

        String s = input.nextLine().replace(" ", "_");

        while(!Objects.equals(s, "Finished")){
            for (int i = 0; i < allIngredients.size(); i++) {
                Ingredient allIngredient = allIngredients.get(i);
                if (Objects.equals(allIngredient.getName().toLowerCase(), s.toLowerCase())) {
                    System.out.println("Ingredient Found! Anything else?");
                    drinkIngredients.add(allIngredient);
                    break;
                }
                if(i == allIngredients.size() - 1){
                    System.out.println("Ingredient not found. Please try again");
                }
            }
            s = input.nextLine().replace(" ", "_");
        }
        drinks.add(new Drink(drinkIngredients, name));
        updatePersistentFile();
        runGUI();

    }

    public void runGUI() {

        //Swing is hard :(
    }


    public void playIntro(){
        String s = """

                ______                  ______               \s
                | ___ \\                 | ___ \\              \s
                | |_/ /_ __ _____      _| |_/ / __ _ ___  ___\s
                | ___ \\ '__/ _ \\ \\ /\\ / / ___ \\/ _` / __|/ _ \\
                | |_/ / | |  __/\\ V  V /| |_/ / (_| \\__ \\  __/
                \\____/|_|  \\___| \\_/\\_/ \\____/ \\__,_|___/\\___|
                                                             \s
                                                             \s
                """;

        System.out.println(s);
        System.out.println("Commands: 'add', 'remove', 'print'");
        System.out.println("Welcome to BrewBase. How can I help you ?");
    }

    private void updatePersistentFile(){
        //this method should update the persistent file to match the drinks list
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(persistText, false));//false indicates overrides file
            for (Drink drink : drinks) {
                pw.println(drink.toString());
            }
            pw.close();
            //for future me when im writing the remove:
            //this will print everything in the array to the text file
            //so i just need to remove the drink from the ArrayList and then call this method and
            //that should work as I intend
            //but first I need to make the toString for the database

            //now the drink array needs to match the text file
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }


    public void delete(){
        System.out.println("What is the index of the item you want deleted? ");

        Scanner input = new Scanner(System.in);
        int deletionIndex = input.nextInt();

        drinks.remove(deletionIndex);

        updatePersistentFile();

        //in theory this sounds easy
        //it is not ;(

    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();

        for (Drink drink : drinks) {
            returnString.append(drink.toString()).append("\n");
        }
        return returnString.toString();
    }

    public static ArrayList<Ingredient> initializeIngredients() {
        try {
            ArrayList<Ingredient> allIngredients = new ArrayList<>();
            Scanner scanner = new Scanner(new File("allIngredients"));
            while(scanner.hasNextLine()){
                String s = scanner.nextLine();
                allIngredients.add(new Ingredient(s));
            }
            return allIngredients;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


      
        return null;
    }
    //get core functionality working

    //implement random drink

    // FILE IO
    //  Text file for all ingredients
    //   Text file for all drinks
    //  I think thats all??? I hope
    // Look into other data persisten options.x

    //implement gui :(

}
