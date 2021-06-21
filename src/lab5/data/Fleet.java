package lab5.data;

import java.util.ArrayList;
import java.util.Scanner;

public class Fleet {
    private static ArrayList<Ship> ships = new ArrayList<>();
    private int budget = 1200;
    private static Scanner input = new Scanner(System.in);

    public Fleet(){
        Steamboat steamboat = new Steamboat(12, "Корабль");
        ships.add(steamboat);
    }



    public int getCount() {
        return ships.size();
    }

    public Ship getShip(int index){
        return ships.get(index);
    }

    public void sellShip(int index){
        budget += ships.get(index).cost;
        ships.remove(index);
    }

    public int getBudget(){
        return budget;
    }

    public void add(Corvette corvette){

        ships.add(corvette);
    }
    public void add(Steamboat steamboat){
        ships.add(steamboat);
    }
    public void add(Sailboat sailboat){
        ships.add(sailboat);
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
