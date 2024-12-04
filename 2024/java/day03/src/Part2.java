import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        try {
            // Specify the input file
            String inputFile = "2024/java/day03/input/input.txt";
            
            // Call the solve2 method to process the file and calculate the result
            long result = solve2(inputFile);
            System.out.println("Total sum of enabled multiplications: " + result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long solve2(String inputFile) throws IOException {
        // Open the input file for reading
        File file = new File(inputFile);
        Scanner scanner = new Scanner(file);
        
        // Regular expressions for mul, do(), and don't() instructions
        String mulRegexStr = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern mulPattern = Pattern.compile(mulRegexStr);
        Pattern searchPattern = Pattern.compile(mulRegexStr + "|do\\(\\)|don't\\(\\)");

        long sum = 0;
        boolean enabled = true;  // Initially, multiplication is enabled

        // Read the file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = searchPattern.matcher(line);

            // Process each match in the line
            while (matcher.find()) {
                String match = matcher.group();

                // Handle do() and don't() instructions
                if (match.equals("do()")) {
                    enabled = true;  // Enable mul instructions
                } else if (match.equals("don't()")) {
                    enabled = false; // Disable mul instructions
                } else if (enabled) {
                    // Process mul(x, y) when enabled
                    Matcher mulMatcher = mulPattern.matcher(match);
                    if (mulMatcher.find()) {
                        int x = Integer.parseInt(mulMatcher.group(1));
                        int y = Integer.parseInt(mulMatcher.group(2));
                        sum += (long) x * y;  // Add the result of the multiplication
                    }
                }
            }
        }

        scanner.close(); // Close the scanner
        return sum; // Return the final sum
    }
}
