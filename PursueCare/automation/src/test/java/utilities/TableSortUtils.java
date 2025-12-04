package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class TableSortUtils {

    /**
     * Detects the data type of a value
     */
    public static String detectType(String value) {
        value = value.trim();

        // Check for empty or null
        if (value.isEmpty() || value.equals("-") || value.equalsIgnoreCase("n/a"))
            return "empty";

        // Time range check (e.g., "7:00 am - 7:15 am")
        if (value.matches("\\d{1,2}:\\d{2}\\s?(am|pm)\\s?-\\s?\\d{1,2}:\\d{2}\\s?(am|pm)"))
            return "time";

        // Number check (including negative and decimals)
        if (value.matches("-?\\d+(\\.\\d+)?"))
            return "number";

        // Currency check (supports multiple symbols)
        if (value.matches("[₹$€£¥]\\s?-?\\d[\\d,]*(\\.\\d+)?"))
            return "currency";

        // Percentage check
        if (value.matches("-?\\d+(\\.\\d+)?%"))
            return "percentage";

        // Date check (multiple formats)
        if (value.matches("\\d{1,2}[-/]\\d{1,2}[-/]\\d{2,4}") ||
            value.matches("\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}"))
            return "date";

        // Default to text
        return "text";
    }

    /**
     * Normalizes a value to a comparable type
     */
    public static Comparable<?> normalizeValue(String value) throws Exception {
        value = value.trim();

        // Handle empty values - treat as empty string (will sort first/last)
        if (value.isEmpty() || value.equals("-") || value.equalsIgnoreCase("n/a"))
            return "";

        // Time range parsing (e.g., "7:00 am - 7:15 am")
        if (value.matches("\\d{1,2}:\\d{2}\\s?(am|pm)\\s?-\\s?\\d{1,2}:\\d{2}\\s?(am|pm)")) {
            // Extract start time only for sorting
            String startTime = value.split("-")[0].trim();
            return parseTimeToMinutes(startTime);
        }

        // Number parsing
        if (value.matches("-?\\d+(\\.\\d+)?"))
            return Double.parseDouble(value);

        // Currency parsing (remove symbols and commas)
        if (value.matches("[₹$€£¥]\\s?-?\\d[\\d,]*(\\.\\d+)?"))
            return Double.parseDouble(value.replaceAll("[₹$€£¥,\\s]", ""));

        // Percentage parsing
        if (value.matches("-?\\d+(\\.\\d+)?%"))
            return Double.parseDouble(value.replace("%", ""));

        // Date parsing with multiple format support
        if (value.matches("\\d{1,2}[-/]\\d{1,2}[-/]\\d{2,4}") ||
            value.matches("\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}")) {

            List<String> patterns = Arrays.asList(
                    "MM/dd/yyyy", "dd/MM/yyyy", "yyyy-MM-dd", 
                    "dd-MM-yyyy", "MM-dd-yyyy", "M/d/yyyy", "d/M/yyyy"
            );

            for (String pattern : patterns) {
                try {
                    return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
                } catch (Exception ignored) {
                    // Try next pattern
                }
            }
        }

        // Default: return as lowercase text for case-insensitive comparison
        return value.toLowerCase();
    }

    /**
     * Converts time string (e.g., "7:00 am") to minutes since midnight for sorting
     */
    private static Integer parseTimeToMinutes(String time) {
        try {
            time = time.trim().toLowerCase();
            boolean isPM = time.contains("pm");
            
            // Remove am/pm and clean up
            time = time.replaceAll("(am|pm)", "").trim();
            
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            
            // Convert to 24-hour format
            if (isPM && hours != 12) {
                hours += 12;
            } else if (!isPM && hours == 12) {
                hours = 0;
            }
            
            return hours * 60 + minutes;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Checks if a column is sorted in the specified order
     * 
     * @param elements List of WebElements from the column
     * @param order "asc" for ascending, "desc" for descending
     * @return true if sorted correctly, false otherwise
     */
    public static boolean isColumnSortedUniversal(List<WebElement> elements, String order) {
        try {
            // Extract text from elements, filter out empty
            List<String> actualText = elements.stream()
                    .map(e -> e.getText().trim())
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            // Handle edge cases
            if (actualText.isEmpty() || actualText.size() == 1) {
                return true; // Single or no elements are always "sorted"
            }

            // Normalize all values
            List<Comparable> actualNormalized = new ArrayList<>();
            for (String val : actualText) {
                actualNormalized.add((Comparable) normalizeValue(val));
            }

            // Debug logging
            System.out.println("=== Column Sort Check ===");
            System.out.println("Order: " + order);
            System.out.println("Actual values: " + actualNormalized);

            // Create expected sorted list
            List<Comparable> expected = new ArrayList<>(actualNormalized);

            if (order.equalsIgnoreCase("asc")) {
                expected.sort(Comparator.nullsLast(Comparator.naturalOrder()));
            } else {
                expected.sort(Comparator.nullsFirst(Comparator.reverseOrder()));
            }

            System.out.println("Expected values: " + expected);
            
            boolean isSorted = actualNormalized.equals(expected);
            System.out.println("Is sorted: " + isSorted);
            System.out.println("========================");

            return isSorted;

        } catch (Exception e) {
            System.err.println("Error checking sort order:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Enhanced version with detailed mismatch reporting
     */
    public static boolean isColumnSortedWithDetails(List<WebElement> elements, String order) {
        try {
            List<String> actualText = elements.stream()
                    .map(e -> e.getText().trim())
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            if (actualText.isEmpty() || actualText.size() == 1) {
                return true;
            }

            List<Comparable> actualNormalized = new ArrayList<>();
            for (String val : actualText) {
                actualNormalized.add((Comparable) normalizeValue(val));
            }

            List<Comparable> expected = new ArrayList<>(actualNormalized);

            if (order.equalsIgnoreCase("asc")) {
                expected.sort(Comparator.nullsLast(Comparator.naturalOrder()));
            } else {
                expected.sort(Comparator.nullsFirst(Comparator.reverseOrder()));
            }

            boolean isSorted = actualNormalized.equals(expected);

            if (!isSorted) {
                System.err.println("SORT CHECK FAILED");
                System.err.println("Expected order: " + order);
                for (int i = 0; i < actualNormalized.size(); i++) {
                    String match = actualNormalized.get(i).equals(expected.get(i)) ? "[OK]" : "[FAIL]";
                    System.err.println(String.format("%s [%d] Actual: %s | Expected: %s", 
                        match, i, actualNormalized.get(i), expected.get(i)));
                }
            }

            return isSorted;

        } catch (Exception e) {
            System.err.println("Error checking sort order:");
            e.printStackTrace();
            return false;
        }
    }
}