package lab5.data;

public abstract class Ship {
    protected float speed;
    protected String name;
    protected String type;
    protected int cost;

    public String getShipInfo() {
        return String.format("\nНазвание корабля: %s\nСкорость корабля - %s узл.\nТип корабля - %s\nСтоимость - %s$", name, speed, type, cost);
    }

    public abstract String getShipFeatures();



    public int getCost() {
        return cost;
    }

    public float getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public String getName(){
        return this.name;
    }

}
