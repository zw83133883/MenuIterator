

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

public class Main {

    public static final int min = 0;
    public static final int max = 8;
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner input = new Scanner(System.in);
        int choice;
        MenuIterator itr;
        generateMenuItem(menu);
        choice = menu(input,min,max);
        while(choice != 0){
            String name;
            int category;
            String ans;
            double price;
            switch (choice) {
                //Case 1 iterates over all the items in Menu object.
                case 1:
                    MenuItem item;
                    itr = menu.getAllItemsIterator();
                    while (itr.hasNext()) {
                        item = itr.next();
                        System.out.println(item.getItemName() + " $" + item.getPrice());
                    }
                    break;
                //Case 2 iterates over appetizer items in the Menu object.
                case 2:
                    MenuItem appItem;
                    itr = menu.getItemIterator(Menu.APPETIZERS);
                    while (itr.hasNext()) {
                        appItem = itr.next();
                        if(appItem.getCategory() == Menu.APPETIZERS)
                             System.out.println(appItem.getItemName() + " $" + appItem.getPrice());
                    }
                    break;
                //Case 3 iterates over main dish items in the Menu objects.
                case 3:
                    MenuItem mainDish;
                    itr = menu.getItemIterator(Menu.MAIN_DISH);
                    while (itr.hasNext()) {
                        mainDish = itr.next();
                        if(mainDish.getCategory() == Menu.MAIN_DISH)
                            System.out.println(mainDish.getItemName() + " $" + mainDish.getPrice());
                    }
                    break;
                //Case 4 iterates over the desert items in the Menu object.
                case 4:
                    MenuItem desertItem;
                    itr = menu.getItemIterator(Menu.DESSERT);
                    while(itr.hasNext()){
                        desertItem = itr.next();
                        if(desertItem.getCategory() == Menu.DESSERT)
                            System.out.println(desertItem.getItemName() +" $" +desertItem.getPrice());
                    }
                    break;
                //Case 5 iterates over the heathly items in the Menu object.
                case 5:
                    itr = menu.getHeartHeathlyIterator();
                    while (itr.hasNext()) {
                        item = itr.next();
                        if (item.getHeartHealthy())
                            System.out.println(item.getItemName() + " $" + item.getPrice());
                    }
                    break;
                //Case 6 iterators over speific items under certain price.
                case 6:
                    MenuItem dishPrice;
                    double speificPrice;
                    System.out.print("Enter price: ");
                    speificPrice = getDouble(input,0,10000);
                    itr = menu.getPriceIterator(speificPrice);
                    while(itr.hasNext()){
                        dishPrice = itr.next();
                        if(dishPrice.getPrice() <= speificPrice )
                            System.out.println(dishPrice.getItemName() +" $" + dishPrice.getPrice());
                    }
                    break;
                //Case 7 adds the food item in the Menu object.
                case 7:
                    System.out.print("\tEnter the name of item: ");
                    name = input.next();
                    System.out.println("\t1 - APPETIZERS\n"+
                                       "\t2 - Main Dish\n" +
                                       "\t3 - Desert\n" +
                                       "\tChoose the category of the item:");
                    category = getInt(input,1,3);
                    System.out.print("Enter the price: ");
                    price = getDouble(input,0,10000);
                    System.out.print("Is it a heart healthy item? yes/no");
                    ans = input.next().toLowerCase();
                    if(ans.equals("yes"))
                        menu.addItem(new MenuItem(name,category,true,price));
                    else if(ans.equals("no"))
                        menu.addItem(new MenuItem(name,category,false,price));
                    else{
                        System.out.println("Invalid.");
                        choice = menu(input,min,max);
                    }
                    break;
                case 8:
                    System.out.println("Enter the name of item: ");
                    name = input.next();
                    if(menu.searchItem(name)){
                        System.out.println("Item has been removed.");
                    }
                    else {
                        System.out.println("Error, Could not find item.");
                        choice = menu(input,min,max);
                    }


            }
            choice = menu(input,min,max);
        }

            System.out.println("Menu Terminated");
    }
    //Menu that displays the options.
    public static int menu(Scanner input, int min,int max){
        int ans;
        System.out.println("\t--------------------------------------------------------\n" +
                           "\t1 – Display all menu items\n" +
                           "\t2 – Display all appetizers\n" +
                           "\t3 – Display all main dishes\n" +
                           "\t4 – Display all desserts\n" +
                           "\t5 – Display all hearty healthy items\n" +
                           "\t6 – Display all main dishes under a specified price\n" +
                           "\t7 - Add items to menu\n"+
                           "\t8 - Remove items from menu\n"+
                           "\t0 - Terminate menu\n" +
                           "\t--------------------------------------------------------\n");
        System.out.print("    Enter your choice: ");;
        ans = getInt(input,min,max);
        return ans;
    }
    //Input validation for interger choices.
    public static int getInt(Scanner input, int min, int max) {
        while (!input.hasNextInt()) {
            System.out.println("Invalid.");
            input.next();
        }
        int choice = input.nextInt();
        if (choice < min || choice > max) {
            System.out.println("Invalid range.");
            choice = getInt(input, min, max);
        }
        return choice;
    }
    //input validation for double.
    public static double getDouble(Scanner input, int min, int max){
        while(!input.hasNextDouble()){
            System.out.println("Invalid");
            input.next();
        }
        double choice = input.nextDouble();
        if(choice < min || choice > max){
            System.out.println("Invalid range.");
            choice = getDouble(input,min,max);
        }
        return choice;
    }
    //method that generates hardcoded menu items.
    public static void generateMenuItem(Menu menu){
        menu.addItem(new MenuItem("Chicken Sandwich", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, 10.95));
        menu.addItem(new MenuItem("Rib eye Steak", Menu.MAIN_DISH, Menu.NOT_HEART_HEALTHY, 19.95));
        menu.addItem(new MenuItem("Fries", Menu.APPETIZERS, Menu.NOT_HEART_HEALTHY, 3.00));
        menu.addItem(new MenuItem("Vegetable salad", Menu.APPETIZERS, Menu.HEART_HEALTHY, 4.00));
        menu.addItem(new MenuItem("Ice cream", Menu.DESSERT, Menu.NOT_HEART_HEALTHY, 3.00));
    }
}
