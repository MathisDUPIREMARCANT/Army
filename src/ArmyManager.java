import java.util.ArrayList;
import java.util.Scanner;

class ArmyManager {
    private final ArrayList<Army> armies = new ArrayList<>();
    private Army currentArmy;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n=== Army Manager ===");
            System.out.println("1. Créer une armée");
            System.out.println("2. Sélectionner une armée");
            System.out.println("3. Ajouter un groupe");
            System.out.println("4. Ajouter une unité");
            System.out.println("5. Supprimer un groupe");
            System.out.println("6. Supprimer une unité");
            System.out.println("7. Supprimer une armée");
            System.out.println("8. Afficher toutes les armées");
            System.out.println("9. Quitter");
            System.out.print("Choix : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createArmy();
                case 2 -> selectArmy();
                case 3 -> addGroup();
                case 4 -> addUnit();
                case 5 -> deleteGroup();
                case 6 -> deleteUnit();
                case 7 -> deleteArmy();
                case 8 -> displayAllArmies();
                case 9 -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private void createArmy() {
        System.out.print("Nom de l'armée : ");
        String name = scanner.nextLine();
        System.out.print("Faction : ");
        String faction = scanner.nextLine();
        System.out.print("Points max : ");
        int maxPoints = scanner.nextInt();
        scanner.nextLine();

        Army newArmy = new Army(name, faction, maxPoints);
        armies.add(newArmy);
        currentArmy = newArmy;
        System.out.println("Armée créée avec succès et sélectionnée comme active.");
    }

    private void selectArmy() {
        if (armies.isEmpty()) {
            System.out.println("Aucune armée disponible.");
            return;
        }
        System.out.println("Sélectionnez une armée (index) :");
        for (int i = 0; i < armies.size(); i++) {
            System.out.println(i + ". " + armies.get(i).getName());
        }

        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= armies.size()) {
            System.out.println("Index invalide.");
        } else {
            currentArmy = armies.get(index);
            System.out.println("Armée sélectionnée : " + currentArmy.getName());
        }
    }

    private void addGroup() {
        if (currentArmy == null) {
            System.out.println("Aucune armée sélectionnée. Veuillez en sélectionner ou en créer une.");
            return;
        }
        System.out.print("Nom du groupe : ");
        String groupName = scanner.nextLine();
        currentArmy.addGroup(new Group(groupName));
        System.out.println("Groupe ajouté.");
    }

    private void addUnit() {
        if (currentArmy == null) {
            System.out.println("Aucune armée sélectionnée. Veuillez en sélectionner ou en créer une.");
            return;
        }

        System.out.println("Groupe où ajouter l'unité (index) :");
        for (int i = 0; i < currentArmy.getGroups().size(); i++) {
            System.out.println(i + ". " + currentArmy.getGroups().get(i).getName());
        }

        int groupIndex = scanner.nextInt();
        scanner.nextLine();

        if (groupIndex < 0 || groupIndex >= currentArmy.getGroups().size()) {
            System.out.println("Index invalide.");
            return;
        }

        System.out.print("Nom de l'unité : ");
        String unitName = scanner.nextLine();
        System.out.print("Coût de l'unité : ");
        int cost = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Type d'unité : 1. Infanterie 2. Véhicule");
        int unitType = scanner.nextInt();
        scanner.nextLine();

        Unit unit;
        if (unitType == 1) {
            unit = new Infantry(unitName, cost);
        } else if (unitType == 2) {
            System.out.print("Capacité de transport (0 si véhicule d'attaque) : ");
            int capacity = scanner.nextInt();
            scanner.nextLine();
            unit = new Vehicle(unitName, cost, capacity);
        } else {
            System.out.println("Type invalide.");
            return;
        }

        currentArmy.getGroups().get(groupIndex).addUnit(unit);
        System.out.println("Unité ajoutée.");
    }

    private void deleteGroup() {
        if (currentArmy == null || currentArmy.getGroups().isEmpty()) {
            System.out.println("Aucun groupe à supprimer.");
            return;
        }

        System.out.println("Sélectionnez un groupe à supprimer (index) :");
        for (int i = 0; i < currentArmy.getGroups().size(); i++) {
            System.out.println(i + ". " + currentArmy.getGroups().get(i).getName());
        }

        int groupIndex = scanner.nextInt();
        scanner.nextLine();

        if (groupIndex < 0 || groupIndex >= currentArmy.getGroups().size()) {
            System.out.println("Index invalide.");
        } else {
            currentArmy.getGroups().remove(groupIndex);
            System.out.println("Groupe supprimé.");
        }
    }

    private void deleteUnit() {
        if (currentArmy == null || currentArmy.getGroups().isEmpty()) {
            System.out.println("Aucune unité à supprimer.");
            return;
        }

        System.out.println("Sélectionnez un groupe (index) :");
        for (int i = 0; i < currentArmy.getGroups().size(); i++) {
            System.out.println(i + ". " + currentArmy.getGroups().get(i).getName());
        }

        int groupIndex = scanner.nextInt();
        scanner.nextLine();

        if (groupIndex < 0 || groupIndex >= currentArmy.getGroups().size()) {
            System.out.println("Index invalide.");
            return;
        }

        Group group = currentArmy.getGroups().get(groupIndex);
        if (group.getUnits().isEmpty()) {
            System.out.println("Aucune unité dans ce groupe.");
            return;
        }

        System.out.println("Sélectionnez une unité à supprimer (index) :");
        for (int i = 0; i < group.getUnits().size(); i++) {
            System.out.println(i + ". " + group.getUnits().get(i).getName());
        }

        int unitIndex = scanner.nextInt();
        scanner.nextLine();

        if (unitIndex < 0 || unitIndex >= group.getUnits().size()) {
            System.out.println("Index invalide.");
        } else {
            group.getUnits().remove(unitIndex);
            System.out.println("Unité supprimée.");
        }
    }

    private void deleteArmy() {
        if (armies.isEmpty()) {
            System.out.println("Aucune armée disponible.");
            return;
        }
        System.out.println("Sélectionnez une armée à supprimer (index) :");
        for (int i = 0; i < armies.size(); i++) {
            System.out.println(i + ". " + armies.get(i).getName());
        }

        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= armies.size()) {
            System.out.println("Index invalide.");
        } else {
            Army removedArmy = armies.remove(index);
            if (currentArmy == removedArmy) {
                currentArmy = null; // Réinitialiser l'armée active si elle est supprimée.
            }
            System.out.println("Armée supprimée.");
        }
    }

    private void displayAllArmies() {
        if (armies.isEmpty()) {
            System.out.println("Aucune armée disponible.");
            return;
        }
        for (Army army : armies) {
            army.printDetails();
        }
    }
}
