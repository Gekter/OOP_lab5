package lab5.data;

public class Corvette extends Ship {
    private int amountOfGuns;

    public Corvette(int amountOfGuns, String name){
        this.amountOfGuns = amountOfGuns;
        this.name = name;
        this.speed = (float) 27;
        this.cost = 500;
        this.type = "Corvette";
    }


    @Override
    public String getShipFeatures() {
        return String.format(String.valueOf(amountOfGuns) + " пушек");
    }
}
