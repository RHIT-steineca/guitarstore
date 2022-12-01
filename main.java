import java.util.HashMap;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        HashMap<String, String> employees = new HashMap<String, String>();
        Inventory inventory = new Inventory();
        Boolean isLoggedIn = false;

        // Import inventory from local file
        Scanner input = new Scanner(System.in);
        while(true) {
            String cmdoptions = "Search For Guitar (S)";
            if(isLoggedIn == true) {
                cmdoptions += " / Add Guitar (A) / Update Price (P) / Update Location (L)";
            } else {
                cmdoptions += " / Employee Login (E)";
            }
            System.out.println("Welcome to Rick's Guitar Store!\n"+
                                "Please enter one of the following commands:\n"+
                                cmdoptions);
            String command = input.nextLine();
        }
    }
}