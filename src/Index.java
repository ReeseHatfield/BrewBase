import java.util.Scanner;

public class Index {
    public static void main(String[] args) {
        BrewBase db = new BrewBase();


        Scanner input = new Scanner(System.in);
        String interaction = input.nextLine().toLowerCase();

        while(!interaction.equalsIgnoreCase("exit")){

            switch (interaction) {
                case "add" -> db.addDrink();
                case "remove" -> {
                    System.out.println("Warning: Only administrator's are permitted to delete items from database");
                    System.out.println("(Try 'sudo remove' next time");
                    System.out.println("Proceed? (y/n");
                    char c = input.nextLine().toLowerCase().charAt(0);
                    if (c == 'y') {
                        db.delete();
                    }
                }
                case "sudo remove" -> db.delete();
                case "print" -> System.out.println(db);
            }

            System.out.println("How can I help you?");
            interaction = input.nextLine().toLowerCase();
        }


        db.addDrink();
        System.out.println(db);
    }
}
