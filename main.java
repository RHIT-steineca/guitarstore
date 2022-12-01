import java.util.HashMap;
import java.util.Scanner;

public class main {
    static HashMap<String, String> employees;
    static Inventory inventory;
    static Boolean isLoggedIn;
    static Scanner input;

    public static void main(String[] args) {
        employees = new HashMap<String, String>();
        inventory = new Inventory();
        isLoggedIn = false;
        
        loadDatabase();

        input = new Scanner(System.in);
        while(true) {
            String cmdoptions = "Search For Guitar (S)";
            if(isLoggedIn) {
                cmdoptions += " / Add Guitar (A) / Remove Guitar (R) / Update Price (P) / Update Location (L) / Employee Logout (Q)";
            } else {
                cmdoptions += " / Employee Login (E)";
            }
            System.out.println("\nWelcome to Rick's Guitar Store!\n"+
                                "Please enter one of the following commands:\n"+
                                cmdoptions);
            String command = input.nextLine();
            parseCommand(command);
        }
    }

    public static void loadDatabase() {
        // load employee list
        // load inventory
        return;
    }

    public static void parseCommand(String command) {
        if(command.equals("S")) {
            handleSearch();
            return;
        }
        if(isLoggedIn) {
            if(command.equals("E")) {
                System.out.println("\nAlready logged in!");
                return;
            } else if(command.equals("Q")) {
                isLoggedIn = false;
                return;
            } else if(command.equals("A")) {
                handleAddGuitar();
                return;
            } else if(command.equals("R")) {
                handleRemoveGuitar();
                return;
            } else if(command.equals("P")) {
                handleUpdatePrice();
                return;
            } else if(command.equals("L")) {
                handleUpdateLocation();
                return;
            }
        } else {
            if(command.equals("E")) {
                handleLogin();
                return;
            } else if(command.equals("Q") || 
                        command.equals("A") || 
                        command.equals("R") || 
                        command.equals("P") || 
                        command.equals("L")) {
                System.out.println("\nNot logged in! ... How do you know this command?");
                return;
            }
        }
        System.out.println("\nSorry, that command does not exist!\n"+
                            "Please enter the single letter in parenthesis "+
                            "next to the command you wish to use.");
        return;
    }

    public static void handleSearch() {
        return;
    }

    public static void handleLogin() {
        isLoggedIn = true;
        return;
    }

    public static void handleAddGuitar() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter the guitar's serial number:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the guitar's price:");
        String price = input.nextLine();
        System.out.println("\nPlease enter the guitar's builder:");
        String builder = input.nextLine();
        System.out.println("\nPlease enter the guitar's model:");
        String model = input.nextLine();
        System.out.println("\nPlease enter the guitar's type:");
        String type = input.nextLine();
        System.out.println("\nPlease enter the guitar's topwood:");
        String topwood = input.nextLine();
        System.out.println("\nPlease enter the guitar's location:");
        String location = input.nextLine();
        inventory.addGuitar(serialnum, price, builder, model, type, topwood, location);
        System.out.println("\nGuitar added!");
        return;
    }

    public static void handleRemoveGuitar() {
        System.out.println("\nPlease enter the serial number of the guitar you wish to remove:");
        String serialnum = input.nextLine();
        inventory.removeGuitar(serialnum);
        System.out.println("\nGuitar removed!");
        return;
    }

    public static void handleUpdatePrice() {
        System.out.println("\nPlease enter the serial number of the guitar you wish to update the price of:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the new price of the guitar:");
        String price = input.nextLine();
        inventory.updatePrice(serialnum, price);
        System.out.println("\nGuitar price updated!");
        return;
    }

    public static void handleUpdateLocation() {
        System.out.println("\nPlease enter the serial number of the guitar you wish to update the location of:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the new location of the guitar:");
        String location = input.nextLine();
        inventory.updateLocation(serialnum, location);
        System.out.println("\nGuitar location updated!");
        return;
    }
}