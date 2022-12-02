import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        
        if(loadDatabase() == 0) {
            return;
        }

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

    public static int loadDatabase() {
        try {
            File empFile = new File("employees.csv");
            Scanner csvscanner = new Scanner(empFile);
            while (csvscanner.hasNextLine()) {
                String[] split = csvscanner.nextLine().split(",");
                employees.put(split[0], split[1]);
            }
            csvscanner.close();
            File invFile = new File("inventory.csv");
            csvscanner = new Scanner(invFile);
            while (csvscanner.hasNextLine()) {
                String[] split = csvscanner.nextLine().split(",");
                inventory.addGuitar(new Guitar(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
            }
            csvscanner.close();
            return 1;
        } catch (FileNotFoundException e) {
            System.out.print("\nDatabase files not found!");
            return 0;
        }
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
        System.out.println("\nPlease enter your username:");
        String user = input.nextLine();
        System.out.println("\nPlease enter your password:");
        String pass = input.nextLine();
        if(employees.containsKey(user)) {
            if(employees.get(user).equals(pass)) {
                isLoggedIn = true;
            } else {
                System.out.println("\nInvalid password.");
            }
        } else {
            System.out.println("\nInvalid user.");
        }
        return;
    }

    public static void handleAddGuitar() {
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
        inventory.addGuitar(new Guitar(serialnum, price, builder, model, type, topwood, location));
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("inventory.csv", true));
            writer.append("\n" + serialnum + "," + price + "," + builder + "," + model + "," + type + "," + topwood + "," + location);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nGuitar added!");
        return;
    }

    public static void handleRemoveGuitar() {
        System.out.println("\nPlease enter the serial number of the guitar you wish to remove:");
        String serialnum = input.nextLine();
        inventory.removeGuitar(serialnum);

        File invFile = new File("inventory.csv");
        File temp = new File("temp.csv");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(invFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            String lineToRemove = serialnum;
            String currentLine;
    
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.split(",")[0].equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            invFile.delete();
            File newinvFile = new File("inventory.csv");
            temp.renameTo(newinvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nGuitar removed!");
        return;
    }

    public static void handleUpdatePrice() {
        System.out.println("\nPlease enter the serial number of the guitar you wish to update the price of:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the new price of the guitar:");
        String price = input.nextLine();
        inventory.updatePrice(serialnum, price);

        File invFile = new File("inventory.csv");
        File temp = new File("temp.csv");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(invFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            String lineToUpdate = serialnum;
            String currentLine;
    
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] split = trimmedLine.split(",");
                if(split[0].equals(lineToUpdate)) {
                    writer.write(split[0] + "," + price + "," + split[2] + "," + split[3] + "," + split[4] + "," + split[5] + "," + split[6] + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            invFile.delete();
            File newinvFile = new File("inventory.csv");
            temp.renameTo(newinvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nGuitar price updated!");
        return;
    }

    public static void handleUpdateLocation() {
        System.out.println("\nPlease enter the serial number of the guitar you wish to update the location of:");
        String serialnum = input.nextLine();
        System.out.println("\nPlease enter the new location of the guitar:");
        String location = input.nextLine();
        inventory.updateLocation(serialnum, location);

        File invFile = new File("inventory.csv");
        File temp = new File("temp.csv");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(invFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            String lineToUpdate = serialnum;
            String currentLine;
    
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] split = trimmedLine.split(",");
                if(split[0].equals(lineToUpdate)) {
                    writer.write(split[0] + "," + split[1] + "," + split[2] + "," + split[3] + "," + split[4] + "," + split[5] + "," + location + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            invFile.delete();
            File newinvFile = new File("inventory.csv");
            temp.renameTo(newinvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nGuitar location updated!");
        return;
    }
}