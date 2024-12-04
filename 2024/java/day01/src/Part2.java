
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day01/input/input.txt"));
            List<String> leftElements = new ArrayList<>();
            List<String> rightElements = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split("\\s+");
                leftElements.add(parts[0]);
                rightElements.add(parts[1]);
            }
            // Collections.sort(leftElements);
            // Collections.sort(rightElements);
            int simScore = 0;
            for (int i = 0; i < leftElements.size(); i++) {
                int ele = Integer.parseInt(leftElements.get(i));
                int count = 0;
                for(int j = 0; j < rightElements.size(); j++) {
                    if (Integer.parseInt(rightElements.get(j)) == ele) {
                        count++;
                    }
                }
                simScore += ele * count;
            }

            System.out.println(simScore);
        } catch (IOException e) {
        }
    }
}
