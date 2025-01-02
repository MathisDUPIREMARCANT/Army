import java.util.ArrayList;

class Army {
    private final String name;
    private final String faction;
    private final int maxPoints;
    private final ArrayList<Group> groups = new ArrayList<>();

    public Army(String name, String faction, int maxPoints) {
        this.name = name;
        this.faction = faction;
        this.maxPoints = maxPoints;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void printDetails() {
        System.out.println("=== Armée : " + name + " ===");
        System.out.println("Faction : " + faction);
        System.out.println("Points max : " + maxPoints);
        System.out.println("Points utilisés : " + calculateTotalPoints());
        System.out.println("Groupes :");
        if (groups.isEmpty()) {
            System.out.println("  Aucun groupe.");
        } else {
            for (Group group : groups) {
                group.printDetails();
            }
        }
    }

    private int calculateTotalPoints() {
        return groups.stream().mapToInt(Group::calculateGroupPoints).sum();
    }
}
