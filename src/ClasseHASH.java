import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HexFormat;

public class ClasseHASH {

    public static String calculateHash(String messatge) {
        try {
            // Convertim l'string a un array de bytes
            byte[] bytesIn = messatge.getBytes(StandardCharsets.UTF_8);

            // Instanciem la classe per fer el hash
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // Calculem el hash
            byte[] hashResult = sha256.digest(bytesIn);

            // Convertim el hash a hexadecimal per mostrar-lo
            String textOut = HexFormat.of().withUpperCase().formatHex(hashResult);
            return textOut;

        } catch (Exception e) {
            System.out.println("Error calculant el hash: " + e.getMessage());
            return null;
        }
    }

    public static String generateSalt() {
        // Creem un array buit de 16 bytes per guardar la salt
        byte[] salt = new byte[16];

        // Instanciem SecureRandom (aleatori segur per a criptografia)
        SecureRandom random = new SecureRandom();

        // Omplim l'array amb bytes aleatoris
        random.nextBytes(salt);

        // Convertim la salt a hexadecimal per poder-la guardar i mostrar
        String textOut = HexFormat.of().withUpperCase().formatHex(salt);
        return textOut;
    }
}
