import java.util.ArrayList;

public class MenuItem{

    private String itemName;
    private int category;
    private boolean heartHealthy;
    private double price;
    ArrayList<MenuItem> arrayList = new ArrayList<>();

    public MenuItem(){
        this.itemName = "";
        this.category = 0;
        this.heartHealthy = true;
        this.price = 0;
    }
    public MenuItem(String itemName, int category, boolean heartHealthy, double price){
        this.itemName = itemName;
        this.category = category;
        this.heartHealthy = heartHealthy;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }


    public double getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }

    public boolean getHeartHealthy() {
        return heartHealthy;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setHeartHealthy(boolean heartHealthy) {
        this.heartHealthy = heartHealthy;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
