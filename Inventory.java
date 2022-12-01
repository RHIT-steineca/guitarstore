import java.util.HashMap;

public class Inventory {
    HashMap<String, Guitar> inventory;

    public void Inventory() {
        this.inventory = new HashMap<String, Guitar>();

    }

    public void updateLocation(String serialnum, String location) {
    }

    public void updatePrice(String serialnum, String price) {
    }

    public void removeGuitar(String serialnum) {
    }

    public void addGuitar(String serialnum, String price, String builder, String model, String type, String topwood,
            String location) {
    }
}
