
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day04/input/input.txt"));
            int row = lines.size();
            int col = lines.get(0).length();
            int[][] directions = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0},
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 1}
            };

            int count = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    for (int[] dir : directions) {
                        String word = "";
                        for (int k = 0; k < 4; k++) {
                            int newRow = i + k * dir[0];
                            int newCol = j + k * dir[1];

                            if (newRow < 0 || newRow >= row || newCol < 0 || newCol >= col) {
                                word = "";
                                break;
                            }

                            word += lines.get(newRow).charAt(newCol);
                        }

                        if (word.equals("XMAS")) {
                            count++;
                        }
                    }
                }
            }

            System.out.println(count);
        } catch (IOException e) {
        }
    }
}
