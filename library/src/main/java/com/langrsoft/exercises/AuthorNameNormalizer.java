package com.langrsoft.exercises;


import com.langrsoft.util.InvalidNameException;

public class AuthorNameNormalizer {

    // hints:
    // - You might try RegEx replace but things might get ugly...
    // - The String method split is useful.
    // - You might find the code simpler later if you use a LinkedList instead of an array.


    public String normalize(String string) {
        if (string == null || string.isEmpty())
            return "";

        String suffix = getSuffix(string);

        String[] parts = getParts(string, suffix);

        String middleInitial = getMiddleInitials(parts, 1);

        switch (parts.length) {
            case 0:
                return "";
            case 1:
                return getFirstName(parts);
            case 2:
                return getLastCommaFirstName(parts);
            default:
                return getLastCommaFirstName(parts) + middleInitial + suffix;
        }
    }

    private static String[] getParts(String string, String suffix) {
        if (!suffix.isEmpty())
            string = string.replace(suffix, "").replace(",", "");
        return string.trim().split(" ");
    }

    private static String getSuffix(String string) {
        String[] suffixParts = string.trim().split(",");

        switch (suffixParts.length) {
            case 1:
                return "";
            case 2:
                return ", " + suffixParts[suffixParts.length - 1].trim();
            default:
                throw new InvalidNameException("name contains 2 commas; maximum is one comma");

        }
    }

    private static String getLastCommaFirstName(String[] parts) {
        return getLastName(parts) + ", " + getFirstName(parts);
    }

    private static String getFirstName(String[] parts) {
        return parts[0];
    }

    private static String getLastName(String[] parts) {
        return parts[parts.length - 1];
    }

    private static String getMiddleInitials(String[] parts, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < parts.length - 1; i++) {
            String middle = parts[i];
            if (middle.length() > 1) {
                sb.append(middle.charAt(0)).append(". ");
            } else {
                sb.append(middle);
            }
        }

        return " " + sb.toString().trim();
    }

    // See http://stackoverflow.com/questions/275944/java-how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
    // ... if you need to convert to < Java 8
    /* private */ long count(String string, char c) {
        return string.chars().filter(ch -> ch == c).count();
    }

}