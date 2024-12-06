
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day06/input/input.txt"));
            int row = lines.size();
            int col = lines.get(0).length();

            int guardRow = -1;
            int guardCol = -1;

            
            int direction = 0;
            int[][] directions = { {-1,0}, {0,1}, {1,0}, {0, -1}};
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (lines.get(i).charAt(j) == '^') {
                        guardRow = i;
                        guardCol = j;
                        break;
                    }
                }
                if (guardRow != -1 && guardCol != -1) {
                    break;
                }
            }
            
            Set<String> visited = new HashSet<>();
            visited.add(guardRow + "," + guardCol);

            while (true) { 
                int nextRow = guardRow + directions[direction][0];
                int nextCol = guardCol + directions[direction][1];

                if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col ){
                    break;
                }

                if(lines.get(nextRow).charAt(nextCol) == '#') {
                    direction = (direction + 1) % 4;
                } else {
                    guardRow = nextRow;
                    guardCol = nextCol;
                    visited.add(guardRow + "," + guardCol);
                }
            }

            int positions = visited.size();
            System.out.println(positions);

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
