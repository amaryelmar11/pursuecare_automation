package BasePage;
import java.util.Random;

public class RandomData {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final Random RAND = new Random();

    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(RAND.nextInt(ALPHABET.length())));
        }
        return sb.substring(0, 1).toUpperCase() + sb.substring(1);
    }

    // --- First Name (always include SidAutomation) ---
    public static String getFirstName() {
        return "SidAutomation" + randomString(3 + RAND.nextInt(3)); // SidAutomation + 3–5 letters
    }

    // --- Middle + Last Name (unchanged) ---
    public static String getMiddleName() {
        return randomString(3 + RAND.nextInt(3));
    }

    public static String getLastName() {
        return randomString(6 + RAND.nextInt(5));
    }

    // --- Phone ---
    public static String getPhone() {
        StringBuilder phone = new StringBuilder();
        phone.append(RAND.nextInt(9) + 1);
        for (int i = 0; i < 9; i++) {
            phone.append(DIGITS.charAt(RAND.nextInt(10)));
        }
        return phone.toString();
    }

    // --- Email (always include SidAutomation) ---
    public static String getEmail() {
        String localPart = "SidAutomation" + RAND.nextInt(10000);
        return localPart.toLowerCase() + "@example.com";
    }

    // --- Full Profile ---
    public static String getFullProfile() {
        String first = getFirstName();
        String middle = getMiddleName();
        String last = getLastName();
        String phone = getPhone();
        String email = getEmail();

        return "Name: " + first + " " + middle + " " + last +
        "\nPhone: " + phone +
        "\nEmail: " + email;
}
}

