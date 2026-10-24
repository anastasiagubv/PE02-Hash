import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaPrincipal {
    Scanner sc = new Scanner(System.in);
    ArrayList<Usuari> users = new ArrayList<>();

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
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Càlcul de resums");
            System.out.println("2. Verificar integritat d'un missatge");
            System.out.println("3. Registrar usuari");
            System.out.println("4. Iniciar sessió");
            System.out.println("5. Prova: login amb una salt diferent");
            System.out.println("0. Sortir");

            String input = readString("Tria una opció: ");

            switch (input.trim()) {
                case "1":
                    hashMenu();
                    break;
                case "2":
                    integrityMenu();
                    break;
                case "3":
                    registerUser();
                    break;
                case "4":
                    login();
                    break;
                case "5":
                    loginWrongSalt();
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

    // Submenú part 1
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

    // Submenú part 2
    public void integrityMenu() {
        boolean exit = false;

        do {
            System.out.println("\n--- Verificar integritat ---");
            System.out.println("1. Enviar missatge (emissor)");
            System.out.println("2. Rebre missatge i verificar (receptor)");
            System.out.println("0. Tornar");

            String input = readString("Tria una opció: ");

            switch (input.trim()) {
                case "1":
                    verifyIntegrity();
                    break;
                case "2":
                    receiveMessage();
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

    // PART 1
    public void hashMessage() {
        String message = readString("\nIntrodueix un missatge: ");
        String hashMessage = ClasseHASH.calculateHash(message);

        System.out.println("Missatge introduït: " + message);
        System.out.println("Missatge en format Hash: " + hashMessage);
    }

    public void compareMessages() {
        String message1 = readString("\nIntrodueix el primer missatge: ");
        String message2 = readString("Introdueix el segon missatge: ");

        String hashMessage1 = ClasseHASH.calculateHash(message1);
        String hashMessage2 = ClasseHASH.calculateHash(message2);

        System.out.println("Missatge 1 introduït: " + message1);
        System.out.println("Missatge 2 introduït: " + message2);

        System.out.println("Missatge 1 en format Hash: " + hashMessage1);
        System.out.println("Missatge 2 en format Hash: " + hashMessage2);

        if (hashMessage1.equals(hashMessage2)) {
            System.out.println("Els missatges Hash coincideixen");
        } else {
            System.out.println("Els missatges Hash no coincideixen");
        }
    }

    public void modifyMessage() {
        String message = readString("\nIntrodueix un missatge: ");
        String hashOriginal = ClasseHASH.calculateHash(message);

        System.out.println("Missatge original: " + message);
        System.out.println("Hash original:     " + hashOriginal);

        // L'usuari modifica el missatge (encara que sigui una sola lletra)
        String modifiedMessage = readString("\nModifica el missatge: ");
        String hashModified = ClasseHASH.calculateHash(modifiedMessage);

        System.out.println("\nMissatge original:  " + message);
        System.out.println("Missatge modificat: " + modifiedMessage);
        System.out.println("Hash original:  " + hashOriginal);
        System.out.println("Hash modificat: " + hashModified);

        if (hashOriginal.equals(hashModified)) {
            System.out.println("El hash no ha canviat (el missatge és el mateix)");
        } else {
            System.out.println("El hash ha canviat completament");
        }
    }

    public void repeatHash() {
        int times = 5;
        String message = readString("\nIntrodueix un missatge: ");

        // Calculem el primer hash per comparar-hi els altres
        String firstHash = ClasseHASH.calculateHash(message);
        boolean same = true;

        System.out.println("Missatge: " + message);

        for (int i = 1; i <= times; i++) {
            String hashMessage = ClasseHASH.calculateHash(message);
            System.out.println("Hash " + i + ": " + hashMessage);

            if (!hashMessage.equals(firstHash)) {
                same = false;
            }
        }

        if (same) {
            System.out.println("El hash es manté igual les " + times + " vegades");
        } else {
            System.out.println("El hash ha canviat en algun càlcul");
        }
    }

    // PART 2
    public void verifyIntegrity() {
        String message = readString("\nIntrodueix missatge: ");
        String hashMessage = ClasseHASH.calculateHash(message);

        // Simulem que algú modifica el missatge pel camí (afegint un caràcter)
        String modifiedMessage = message + "!";

        try {
            Files.writeString(Path.of("missatge.txt"), message);
            Files.writeString(Path.of("missatge.hash"), hashMessage);
            Files.writeString(Path.of("missatgeCanviat.txt"), modifiedMessage);

            System.out.println("Missatge enviat i guardat a missatge.txt");
            System.out.println("Hash original guardat a missatge.hash: " + hashMessage);
            System.out.println("Versió modificada guardada a missatgeCanviat.txt: " + modifiedMessage);

        } catch (Exception e) {
            System.out.println("Error en guardar en fitxer: " + e.getMessage());
        }
    }

    public void receiveMessage() {
        System.out.println("--- Recepció del missatge ---");
        String fileName = readString("Introdueix el nom de l'arxiu (Ex: missatge.txt): ");

        try {
            String receivedMessage = Files.readString(Path.of(fileName));

            String hashFile = ClasseHASH.calculateHash(receivedMessage);

            // trim() treu possibles salts de línia perquè l'equals no falli
            String originalHash = Files.readString(Path.of("missatge.hash")).trim();

            System.out.println("Hash del missatge rebut: " + hashFile);
            System.out.println("Hash del missatge original: " + originalHash);

            if (hashFile.equals(originalHash)) {
                System.out.println("Els hashes del missatge coincideixen");
            } else {
                System.out.println("S'ha detectat una modificació");
            }
        } catch (Exception e) {
            System.out.println("Error: no s'ha trobat el fitxer" + "\n" + e.getMessage());
        }
    }

    // PART 3
    public void registerUser() {
        System.out.println("--- Registre d'usuari ---");

        String userName = "";
        do {
            userName = readString("\nIntrodueix el nom d'usuari: ");

            if (existsUser(userName)) {
                System.out.println("Error: aquest usuari ja existeix");
            }

        } while (existsUser(userName));

        String password = readString("Introdueix la contrasenya: ");

        // Salt aleatòria per a cada usuari: la mateixa contrasenya dona hashes diferents
        String salt = ClasseHASH.generateSalt();
        String hash = ClasseHASH.calculateHash(password + salt);

        // Només es guarden el nom, la salt i el hash (mai la contrasenya)
        Usuari user = new Usuari(userName, salt, hash);
        users.add(user);

        System.out.println("Usuari guardat correctament!");
        System.out.println("Usuari: " + userName);
        System.out.println("Salt: " + salt);
        System.out.println("Hash: " + hash);
    }

    public void login() {
        System.out.println("--- Iniciar Sessió ---");

        String userName = readString("\nIntrodueix el teu nom d'usuari: ");

        // En cas de que no existeixi l'usuari
        if (!existsUser(userName)) {
            System.out.println("Error: l'usuari no està registrat");
            return;
        }

        String password = readString("Introdueix la teva contrasenya: ");

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)) {

                String hash = users.get(i).getHash();
                String salt = users.get(i).getSalt();

                // Amb la salt guardada, si la contrasenya és correcta surt el mateix hash
                String newHash = ClasseHASH.calculateHash(password + salt);

                if (hash.equals(newHash)) {
                    System.out.println("Accés permès. Benvingut/da, " + userName);
                } else {
                    System.out.println("Error: contrasenya incorrecta");
                }
            }
        }
    }

    public void loginWrongSalt() {
       System.out.println("--- Prova: login amb una salt diferent ---");

        String userName = readString("\nIntrodueix el teu nom d'usuari: ");

        // En cas de que no existeixi l'usuari
        if (!existsUser(userName)) {
            System.out.println("Error: l'usuari no està registrat");
            return;
        }

        String password = readString("Introdueix la teva contrasenya: ");

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)) {

                String hash = users.get(i).getHash();
                String salt = users.get(i).getSalt();

                // Fem servir una salt nova en lloc de la guardada per veure què passa
                String wrongSalt = ClasseHASH.generateSalt();
                String newHash = ClasseHASH.calculateHash(password + wrongSalt);

                System.out.println("\nSalt guardat: " + salt);
                System.out.println("Salt diferent: " + wrongSalt);
                System.out.println("Hash guardat: " + hash);
                System.out.println("Hash calculat: " + newHash);

                if (hash.equals(newHash)) {
                    System.out.println("Accés permès. Benvingut/da, " + userName);
                } else {
                    System.out.println("Accés denegat: amb una salt diferent el hash no coincideix");
                }
            }
        } 
    }

    // Recorre la llista i retorna true si troba un usuari amb aquest nom
    public boolean existsUser(String userName) {
        boolean exists = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)) {
                exists = true;
            }
        }

        return exists;
    }
}
