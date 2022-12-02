public class Guitar {
    public String serialNumber;
    public String price;
    public String builder;
    public String model;
    public String type;
    public String topwood;
    public String location;

    public Guitar(String serialnum, String price, String builder, String model, String type, String topwood, String location) {
        this.serialNumber = serialnum;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.topwood = topwood;
        this.location = location;
    }
}