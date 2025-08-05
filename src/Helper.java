import java.util.regex.Pattern;

public class Helper {
    /**
     * toTitleCase takes in a String input and formats it into TitleCase
     *
     * @param input String to be TitleCased
     * @return String that has been formatted into TitleCase
     */
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
    /**
     * isValidEmail method verifies the format of String email (<data3> field)
     *
     * @param email String email <data3> split from the payload
     * @return boolean true/false if email passes regex checks
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;

        // Non-email pattern: only letters, digits, underscores
        String nonEmailRegex = "^[A-Za-z0-9_]+$";

        // Email pattern based on spec
        String emailRegex = "^(?![.-])" +                            // Local part must not start with . _ -
                "(?!.*[.-]{2})" +                         // No consecutive . or -
                "[A-Za-z0-9._-]+" +                       // Local part characters
                "(?<![.-])@" +                           // Local part must not end with . _ -
                "(?![.-])" +                              // Domain must not start with . or -
                "(?!.*[.-]{2})" +                         // No consecutive . or - in domain
                "[A-Za-z0-9.-]+" +                        // Domain characters
                "(?<![.-])\\." +                          // Domain must not end with . or -
                "[a-z]{2,3}$";                            // Extension: 2–3 lowercase letters
        return Pattern.matches(nonEmailRegex, email) || Pattern.matches(emailRegex, email);
    }
    public static boolean matchesNonEmailRegex(String email) {
        // Non-email pattern: only letters, digits, underscores
        String nonEmailRegex = "^[A-Za-z0-9_]+$";
        return Pattern.matches(nonEmailRegex, email);
    }

}
