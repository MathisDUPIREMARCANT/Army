abstract class Unit {
    private final String name;
    private final int cost;

    public Unit(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public abstract void printDetails();
}
