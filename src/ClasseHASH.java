import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
}
