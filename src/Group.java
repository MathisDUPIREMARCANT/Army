import java.util.ArrayList;

class Group {
    private final String name;
    private final ArrayList<Unit> units = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void printDetails() {
        System.out.println("  - Groupe : " + name);
        if (units.isEmpty()) {
            System.out.println("    Aucunité dans ce groupe.");
        } else {
            for (Unit unit : units) {
                unit.printDetails();
            }
        }
    }

    public int calculateGroupPoints() {
        return units.stream().mapToInt(Unit::getCost).sum();
    }
}
