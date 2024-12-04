import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day04/input/input.txt"));
            int row = lines.size();
            int col = lines.get(0).length();
            int count = 0;

            for (int i = 1; i < row - 1; i++) {
                for (int j = 1; j < col - 1; j++) {
                    String firstDiagonal = "" + lines.get(i - 1).charAt(j - 1) + lines.get(i).charAt(j) + lines.get(i + 1).charAt(j + 1);
                    String secondDiagonal = "" + lines.get(i - 1).charAt(j + 1) + lines.get(i).charAt(j) + lines.get(i + 1).charAt(j - 1);

                    if (firstDiagonal.equals("MAS") && secondDiagonal.equals("MAS")) {
                        count++;
                    }
                    if (firstDiagonal.equals("SAM") && secondDiagonal.equals("SAM")) {
                        count++;
                    }
                    if (firstDiagonal.equals("SAM") && secondDiagonal.equals("MAS")) {
                        count++;
                    }
                    if (firstDiagonal.equals("MAS") && secondDiagonal.equals("SAM")) {
                        count++;
                    }
                }
            }

            System.out.println(count);
        } catch (IOException e) {
        }
    }
}