import java.util.Scanner;

public class ProgramaPrincipal {
    Scanner sc = new Scanner(System.in);

    String message = "";

    public static void main(String[] args) {
        ProgramaPrincipal s = new ProgramaPrincipal();
        s.start();
    }

    public void start() {
        menu();
    }

    public String readString(String prompt) {
        String input = "";
        boolean valid = false;

        do {
            System.out.print(prompt);
            try {
                input = sc.nextLine();

                if (input.trim().isEmpty()) {
                    System.out.println("Error: no pot estar buit. Torna-ho a provar.");
                    valid = false;
                } else {
                    valid = true;
                }

            } catch (Exception e) {
                System.out.println("Error: no s'ha pogut llegir l'entrada.");
                valid = false;
            }
        } while (!valid);

        return input;
    }

    public void menu() {
        boolean exit = false;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Càlcul de resums");
            System.out.println("2. Verificar integritat d'un missatge");
            System.out.println("3. Registrar usuari");
            System.out.println("4. Iniciar sessió");
            System.out.println("0. Sortir");

            String input = readString("Tria una opció: ");

            switch (input.trim()) {
                case "1":
                    hashMenu();
                    break;
                case "2":
                    verifyIntegrity();
                    break;
                case "3":
                    registerUser();
                    break;
                case "4":
                    login();
                    break;
                case "0":
                    System.out.println("Sortint del programa");
                    exit = true;
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
                    break;
            }
        } while (!exit);
    }

    // Submenú
    public void hashMenu() {
        boolean exit = false;

        do {
            System.out.println("\n--- Càlcul de resums ---");
            System.out.println("1. Calcular el hash d'un missatge");
            System.out.println("2. Comparar el hash de dos missatges");
            System.out.println("3. Modificar un missatge i comparar hashes");
            System.out.println("4. Calcular diverses vegades el mateix hash");
            System.out.println("0. Tornar");

            String input = readString("Tria una opció: ");

            switch (input.trim()) {
                case "1":
                    hashMessage();
                    break;
                case "2":
                    compareMessages();
                    break;
                case "3":
                    modifyMessage();
                    break;
                case "4":
                    repeatHash();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
                    break;
            }
        } while (!exit);
    }

    public void hashMessage() {
        String message = readString("\nIntrodueix un missatge: ");
        String encrypted = ClasseHASH.calculateHash(message);

        System.out.println("Missatge introduït: " + message);
        System.out.println("Missatge encriptat: " + encrypted);
    }

    public void compareMessages() {
        System.out.println("(pendent)");
    }

    public void modifyMessage() {
        System.out.println("(pendent)");
    }

    public void repeatHash() {
        System.out.println("(pendent)");
    }

    public void verifyIntegrity() {
        System.out.println("(pendent)");
    }

    public void registerUser() {
        System.out.println("(pendent)");
    }

    public void login() {
        System.out.println("(pendent)");
    }
}
