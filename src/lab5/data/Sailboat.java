package lab5.data;

public class Sailboat extends Ship {
    private int amountOfMasts;

    public Sailboat(int amountOfMasts, String name) {
        this.amountOfMasts = amountOfMasts;
        this.name = name;
        this.speed = (float) 13.5;
        this.type = "Sailboat";
        this.cost = 300;
    }


    @Override
    public String getShipFeatures() {
        return String.format(String.valueOf(amountOfMasts) + " мачт");
    }
}
