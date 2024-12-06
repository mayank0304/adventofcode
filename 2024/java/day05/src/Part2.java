import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Part2 {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("2024/java/day05/input/input.txt"));

          // Maps to store the ordering rules
          Map<Integer, Set<Integer>> orderingRules = new HashMap<>();
          List<List<Integer>> updates = new ArrayList<>();
          boolean isOrderingSection = true;

          // Process each line
          for (String line : lines) {
              if (line.trim().isEmpty()) {
                  isOrderingSection = false; // Switch to update section when we hit an empty line
                  continue;
              }

              if (isOrderingSection) {
                  // Process ordering rules (X|Y)
                  String[] parts = line.split("\\|");
                  int before = Integer.parseInt(parts[0].trim());
                  int after = Integer.parseInt(parts[1].trim());

                  // Add the rule that before must be printed before after
                  orderingRules.putIfAbsent(before, new HashSet<>());
                  orderingRules.get(before).add(after);
              } else {
                  // Process update (comma-separated list of page numbers)
                  String[] pageNumbers = line.split(",");
                  List<Integer> update = new ArrayList<>();
                  for (String page : pageNumbers) {
                      update.add(Integer.parseInt(page.trim()));
                  }
                  updates.add(update);
              }
          }

          // Now let's process each update and check if it follows the rules
          int totalMiddlePages = 0;
          for (List<Integer> update : updates) {
              if (!isCorrectOrder(update, orderingRules)) {
                 List<Integer> reorder = orderUpdate(update, orderingRules);
                  int middlePage = getMiddlePage(reorder);
                  totalMiddlePages += middlePage;
              } 
          }

          System.out.println("Total of middle pages from correctly ordered updates: " + totalMiddlePages);

      } catch (IOException e) {
          System.err.println("Error reading file: " + e.getMessage());
      }
  }

  // Check if the update list is in correct order according to the ordering rules
  private static boolean isCorrectOrder(List<Integer> update, Map<Integer, Set<Integer>> orderingRules) {
      // Create a map to track the position of each page in the update
      Map<Integer, Integer> pagePositions = new HashMap<>();
      for (int i = 0; i < update.size(); i++) {
          pagePositions.put(update.get(i), i);
      }

      // Now check if all ordering rules are followed within the update
      for (Map.Entry<Integer, Set<Integer>> entry : orderingRules.entrySet()) {
          int before = entry.getKey();
          Set<Integer> afterPages = entry.getValue();

          // If 'before' page is in the update, check its order with the 'after' pages
          if (pagePositions.containsKey(before)) {
              int beforePosition = pagePositions.get(before);
              for (int after : afterPages) {
                  if (pagePositions.containsKey(after)) {
                      int afterPosition = pagePositions.get(after);
                      // If 'before' page appears after 'after' page, the order is incorrect
                      if (beforePosition > afterPosition) {
                          return false;
                      }
                  }
              }
          }
      }

      return true; // No violations found, update is in correct order
  }

  private static List<Integer> orderUpdate(List<Integer> update, Map<Integer, Set<Integer>> orderingRules) {
    ArrayList<Integer> result = new ArrayList<>(update);
    result.sort((a,b) -> {
        if(orderingRules.containsKey(a) && orderingRules.get(a).contains(b)) {
            return -1;
        } else if (orderingRules.containsKey(b) && orderingRules.get(b).contains(a)) {
                return 1; // 'b' must come before 'a'
            } else {
                return 0; // Keep the relative order
            }
    });
    return result;
  }

  // Get the middle page from the list of pages
  private static int getMiddlePage(List<Integer> update) {
      int size = update.size();
      int midIndex = size / 2;
      return update.get(midIndex - (size % 2 == 0 ? 1 : 0));
  }
}