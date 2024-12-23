import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Part1 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day07/input/input.txt"));
            Map<Long, Set<Integer>> calib = new LinkedHashMap<>();
            for (String line : lines) {
                String[] parts = line.split(":");
                Long calibKey = Long.valueOf(parts[0]);
                calib.putIfAbsent(calibKey, new LinkedHashSet<>());
                String[] part = parts[1].trim().split(" ");
                for( String p : part) {
                    calib.get(calibKey).add(Integer.valueOf(p));
                }
            }

            int total = 0;

            for (Map.Entry<Long, Set<Integer>> en : calib.entrySet()) {
                Long key = en.getKey();
                Set<Integer> val = en.getValue();

                System.out.println(key);
                System.out.println(val);
                if (checkCombinations(val, key)) {
                    total += key; // Add the key to the total if the equation is valid
                }
            }

            System.out.println(total);
        } catch (IOException e) {
        }
    }

    private static boolean checkCombinations(Set<Integer> numbers, Long target){
        Integer[] numArray = numbers.toArray(Integer[]::new);
        return evaluateCombinations(numArray, target, 1, numArray[0]);
    }

    private static boolean evaluateCombinations(Integer[] nums, Long target, int index, int currenaVlue) {
        if (index == nums.length) {
            return currenaVlue == target;
        }
        boolean add = evaluateCombinations(nums, target, index + 1, currenaVlue + nums[index]);

        boolean mul = evaluateCombinations(nums, target, index + 1, currenaVlue * nums[index]);

        return add || mul;

    }
}
