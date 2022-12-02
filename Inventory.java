import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    HashMap<String, Guitar> guitars = new HashMap<String, Guitar>();
    HashMap<String, ArrayList<Guitar>> priceGuitars = new HashMap<String, ArrayList<Guitar>>();
    HashMap<String, ArrayList<Guitar>> builderGuitars = new HashMap<String, ArrayList<Guitar>>();
    HashMap<String, ArrayList<Guitar>> modelGuitars = new HashMap<String, ArrayList<Guitar>>();
    HashMap<String, ArrayList<Guitar>> typeGuitars = new HashMap<String, ArrayList<Guitar>>();
    HashMap<String, ArrayList<Guitar>> topwoodGuitars = new HashMap<String, ArrayList<Guitar>>();
    HashMap<String, ArrayList<Guitar>> locationGuitars = new HashMap<String, ArrayList<Guitar>>();


    public void addGuitar(Guitar g){
        guitars.put(g.serialNumber, g);
        this.sortGuitars(false);
        return;
    }

    public void removeGuitar(String serialNumber){
        guitars.remove(serialNumber);
        this.sortGuitars(false);
        return;
    }

    public void updateLocation(String serialNumber, String location){
        this.guitars.get(serialNumber).location = location;
        this.sortGuitars(true);
    }

    public void updatePrice(String serialNumber, String price){
        this.guitars.get(serialNumber).price = price;
        this.sortGuitars(true);
    }

    public ArrayList<Guitar> searchGuitars(String spec, SPEC s){
        switch(s) {
            case SERIALNUMBER:
                ArrayList<Guitar> guitar = new ArrayList<Guitar>();
                guitar.add(this.guitars.get(spec));
                return guitar;
            case PRICE:
                return this.priceGuitars.get(spec);
            case BUILDER:
                return this.builderGuitars.get(spec);
            case MODEL:
                return this.modelGuitars.get(spec);
            case TYPE:
                return this.typeGuitars.get(spec);
            case TOPWOOD:
                return this.topwoodGuitars.get(spec);
            case LOCATION:
                return this.locationGuitars.get(spec);
            default: 
                return null;
        }
    }

    private void sortGuitars(boolean clear){
        if(clear){
            priceGuitars = new HashMap<String, ArrayList<Guitar>>();
            builderGuitars = new HashMap<String, ArrayList<Guitar>>();
            modelGuitars = new HashMap<String, ArrayList<Guitar>>();
            typeGuitars = new HashMap<String, ArrayList<Guitar>>();
            topwoodGuitars = new HashMap<String, ArrayList<Guitar>>();
            locationGuitars = new HashMap<String, ArrayList<Guitar>>();
        }
       
        for(String num : guitars.keySet()){
            Guitar g = guitars.get(num);

            //sort price
            if(this.guitars.containsKey(g.price)){
                this.priceGuitars.get(g.price).add(g);
            }else{
                ArrayList<Guitar> arr = new ArrayList<Guitar>();
                arr.add(g);
                this.priceGuitars.put(g.price, arr);
            }

            //sort builder
            if(this.guitars.containsKey(g.builder)){
                this.builderGuitars.get(g.builder).add(g);
            }else{
                ArrayList<Guitar> arr = new ArrayList<Guitar>();
                arr.add(g);
                this.builderGuitars.put(g.builder, arr);
            }

            //sort model
            if(this.guitars.containsKey(g.model)){
                this.modelGuitars.get(g.model).add(g);
            }else{
                ArrayList<Guitar> arr = new ArrayList<Guitar>();
                arr.add(g);
                this.modelGuitars.put(g.model, arr);
            }

            //sort type
            if(this.guitars.containsKey(g.type)){
                this.typeGuitars.get(g.type).add(g);
            }else{
                ArrayList<Guitar> arr = new ArrayList<Guitar>();
                arr.add(g);
                this.typeGuitars.put(g.type, arr);
            }

            //sort topwood
            if(this.guitars.containsKey(g.topwood)){
                this.topwoodGuitars.get(g.topwood).add(g);
            }else{
                ArrayList<Guitar> arr = new ArrayList<Guitar>();
                arr.add(g);
                this.topwoodGuitars.put(g.builder, arr);
            }

            //sort location
            if(this.guitars.containsKey(g.location)){
                this.locationGuitars.get(g.builder).add(g);
            }else{
                ArrayList<Guitar> arr = new ArrayList<Guitar>();
                arr.add(g);
                this.locationGuitars.put(g.location, arr);
            }
        }
    }

}

enum SPEC{
    SERIALNUMBER,
    PRICE,
    BUILDER,
    MODEL,
    TYPE,
    TOPWOOD,
    LOCATION
}
