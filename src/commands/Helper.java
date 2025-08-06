package commands;

import java.util.regex.Pattern;

/**
 * Helper class provides utility methods for string formatting and regex checks.
 * It includes:
 * 1. toTitleCase() for title case conversions for <data> fields
 * 2. isValidEmail() for regex checks for valid email formats
 */
public class Helper {
    /**
     * toTitleCase takes in a String input and formats it into TitleCase
     * if the String is null/empty/contains "@", return same input
     *
     * @param input String to be TitleCased
     * @return String that has been formatted into TitleCase
     */
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty() || input.contains("@")) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * isValidEmail method verifies the format of String email (<data3> field)
     * regex checks for either valid email or valid non-email formats
     *
     * @param email String email <data3> split from the payload
     * @return boolean true/false if email passes regex checks
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;

        // Non-email pattern: only letters, digits, underscores
        String nonEmailRegex = "^[A-Za-z0-9_]+$";

        // Email pattern based on spec
        String emailRegex = "^(?![.-])" +                  // Local part must not start with . or -
                "(?!.*[.-]{2})" +                         // No consecutive . or -
                "[A-Za-z0-9._-]+" +                       // Local part characters
                "(?<![.-])@" +                           // Local part must not end with . or -
                "(?![.-])" +                              // Domain1 must not start with . or -
                "(?!.*[.-]{2})" +                         // No consecutive . or - in Domain1
                "[A-Za-z0-9.-]+" +                        // Domain1 characters
                "(?<![.-])\\." +                          // Domain1 must not end with . or -
                "[a-z]{2,3}$";                            // Domain2: 2–3 lowercase letters
        return Pattern.matches(nonEmailRegex, email) || Pattern.matches(emailRegex, email);
    }

}
