class Infantry extends Unit {
    public Infantry(String name, int cost) {
        super(name, cost);
    }

    @Override
    public void printDetails() {
        System.out.println("    * Infanterie : " + getName() + " (" + getCost() + " pts)");
    }
}
