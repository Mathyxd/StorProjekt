package UI;
public abstract class BaseUi {
    protected final Scanner scanner;

    public BaseUi(Scanner scanner) {
        this.scanner = scanner;
    }

    protected int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            System.out.println("Skriv et gyldigt tal.");
            scanner.nextLine();
        }
    }

    protected String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    protected double readDouble(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }
            System.out.println("Skriv et gyldigt beløb.");
            scanner.nextLine();
        }
    }

    protected void pressEnterToContinue() {
        System.out.println("\nTryk Enter for at fortsætte...");
        scanner.nextLine();
    }
}
