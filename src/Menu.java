

import javafx.scene.input.Mnemonic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class Menu {
    private ArrayList<MenuItem> arrayList;
    private MenuItem menuItem;

    public static final int APPETIZERS = 1;
    public static final int MAIN_DISH = 2;
    public static final int DESSERT = 3;
    public static final boolean HEART_HEALTHY = true;
    public static final boolean NOT_HEART_HEALTHY = false;

    public Menu(){
        //Default Constructor
        arrayList = new ArrayList<MenuItem>();
    }

    public void addItem (MenuItem menuItem){
        //Adding an MenuItem object to the arrayList every time when the client calls this function.
        arrayList.add(menuItem);
    }
    public void removeItem(MenuItem menuItem){
        //Removing an MenuItem object to the arrayList
        arrayList.remove(menuItem);
    }
    //Search the item in the arraylist, if its found, remove it and return true. Else, return false.
    public boolean searchItem(String name){
        for(MenuItem menuItem: arrayList){
            if(menuItem.getItemName().equals(name)){
                removeItem(menuItem);
                return true;
            }
        }
        return false;
    }
    //All item iterator iterates all the items in the array list.
    private class AllItemIterator implements MenuIterator {
        MenuItem menuItems;
        int count = 0;

        public AllItemIterator(MenuItem menuItems){
            this.menuItems = menuItem;
        }

        @Override
        public boolean hasNext() {
            if(count > arrayList.size() -1 || arrayList.isEmpty()) //arraylist.size()-1 prevents the arrayOutOfBound Exception.
                return false;
            else{
                return true;
            }
        }
        @Override
        public MenuItem next() {
            if(count < 0)throw new NoSuchElementException();
            else {
                return arrayList.get(count++); //Iterates the arraylist.
            }
        }
    }
    //Helper method to return the AllItemIterator.
    public MenuIterator getAllItemsIterator(){
        return new AllItemIterator(menuItem);
    }
    //Item iterator iterates specific items in the array list.
    private class ItemIterator implements MenuIterator{
        MenuItem menuItem;
        int count = 0;
        int category;

        public ItemIterator(int category, MenuItem menuItem){
            this.category = category;
            this.menuItem = menuItem;
        }
        @Override
        public boolean hasNext() {
            if(count > arrayList.size()-1 || arrayList.isEmpty()){
                return false;
            }
            else
                return true;
        }

        @Override
        public MenuItem next() {
            if(count < 0)throw new NoSuchElementException();
            else {
                return arrayList.get(count++);
            }
        }
    }
    //Helper method
    public MenuIterator getItemIterator(int category){
        return new ItemIterator(category,menuItem);
    }

    //Heart healthy iterator only iterates healthy items in the array list.
    private class HeartHealthyIterator implements MenuIterator {
        MenuItem menuItem;
        int count = 0;

        public HeartHealthyIterator(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public boolean hasNext() {
            if (count > arrayList.size() - 1 || arrayList.isEmpty()||NOT_HEART_HEALTHY) {
                return false;
            } else
                return true;
        }

        @Override
        public MenuItem next() {
            if (count < 0) throw new NoSuchElementException();
            else {
                return arrayList.get(count++);
            }
        }
    }
    //Helper method
    public MenuIterator getHeartHeathlyIterator() {
        return new HeartHealthyIterator(menuItem);
    }

    //Price iterator iterates over certain price below the user input.
    private class PriceIterator implements MenuIterator{
        MenuItem menuItem;
        int count = 0;
        double price;

        public PriceIterator(double price, MenuItem menuItem){
            this.price = price;
            this.menuItem = menuItem;
        }

        @Override
        public boolean hasNext() {
            if (count > arrayList.size() - 1 || arrayList.isEmpty()) {
                return false;
            } else
                return true;
        }

        @Override
        public MenuItem next() {
            if (count < 0) throw new NoSuchElementException();
            else {
                return arrayList.get(count++);
            }
        }
    }
    //Helper method
    public MenuIterator getPriceIterator(double price){
        return new PriceIterator(price,menuItem);
    }
}
