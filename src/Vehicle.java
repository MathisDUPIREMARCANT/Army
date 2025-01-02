class Vehicle extends Unit {
    private final int capacity;

    public Vehicle(String name, int cost, int capacity) {
        super(name, cost);
        this.capacity = capacity;
    }

    @Override
    public void printDetails() {
        System.out.println("    * Véhicule : " + getName() + " (" + getCost() + " pts, capacité : " + capacity + ")");
    }
}
