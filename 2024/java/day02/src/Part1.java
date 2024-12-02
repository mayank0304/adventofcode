
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 extends Method{

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/day02/input/input.txt"));
            List<List<Integer>> data = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                List<Integer> report = new ArrayList<>();
                for (String part : parts) {
                    report.add(Integer.parseInt(part));
                }
                data.add(report);
            }
            int count = 0;
            for (List<Integer> e : data) {
                if (isValid(e)) {
                    count++;
                } 
            }
            System.out.println(count);
        } catch (IOException e) {
        }
    }

    
}
