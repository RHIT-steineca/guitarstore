import java.util.HashMap;
import java.util.Scanner;

public class main {
    static HashMap<String, String> employees;
    static Inventory inventory;
    static Boolean isLoggedIn;
    public static void main(String[] args) {
        employees = new HashMap<String, String>();
        inventory = new Inventory();
        isLoggedIn = false;
        
        loadDatabase();

        while(true) {
            Scanner input = new Scanner(System.in);
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
            input.close();
        }
    }

    public static void loadDatabase() {
        // load employee list
        // load inventory
        return;
    }

    public static void parseCommand(String command) {
        if(command.equals("S")) {
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

    public static void handleLogin() {
        isLoggedIn = true;
        return;
    }

    public static void handleAddGuitar() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter:");
        String serialnum = input.nextLine();
        String price = input.nextLine();
        String builder = input.nextLine();
        String model = input.nextLine();
        String type = input.nextLine();
        String topwood = input.nextLine();
        String location = input.nextLine();
        inventory.addGuitar(serialnum, price, builder, model, type, topwood, location);
        input.close();
        return;
    }

    public static void handleRemoveGuitar() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter the serial number of the guitar you wish to remove:");
        String serialnum = input.nextLine();
        inventory.removeGuitar(serialnum);
        input.close();
        return;
    }

    public static void handleUpdatePrice() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter the serial number of the guitar you wish to update the price of:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the new price of the guitar:");
        String price = input.nextLine();
        inventory.updatePrice(serialnum, price);
        input.close();
        return;
    }

    public static void handleUpdateLocation() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter the serial number of the guitar you wish to update the location of:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the new location of the guitar:");
        String location = input.nextLine();
        inventory.updateLocation(serialnum, location);
        input.close();
        return;
    }
}