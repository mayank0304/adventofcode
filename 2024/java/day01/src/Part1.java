
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part1{
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/day01/input/input.txt"));
            List<String> leftElements = new ArrayList<>();
            List<String> rightElements = new ArrayList<>();
            for (String line : lines ) {
                String[] parts = line.split("\\s+");
                leftElements.add(parts[0]);
                rightElements.add(parts[1]);
            }
            Collections.sort(leftElements);
            Collections.sort(rightElements);

            int totalAnswer = 0;

            for(int i = 0; i < leftElements.size(); i++) {
                int a = Math.abs(Integer.parseInt(leftElements.get(i))  - Integer.parseInt(rightElements.get(i)));
                totalAnswer += a;
            }

            System.out.println(totalAnswer);
        } catch (IOException e) {
        }
    }
}