import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1{
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day03/input/input.txt"));
            String regex = "mul\\((\\d+),(\\d+)\\)";
            Pattern pattern = Pattern.compile(regex);
            int total = 0;
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    total += mul(x,y);
                }

            }
            System.out.println(total);
        } catch (IOException e) {
        }
    }
    private static int mul(int x, int y) {
        return x * y;
    }
}