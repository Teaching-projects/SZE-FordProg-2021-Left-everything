package lefteverything;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {

    public Map<String, List<String>> readFile(String path) throws FileNotFoundException {
        Map<String, List<String>> grammar = new HashMap<>();
        try {
            File rules = new File(path);
            Scanner scanner = new Scanner(rules);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                createRuleMap(grammar, data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return grammar;
    }

    private void createRuleMap(Map<String, List<String>> map, String data) {
        String[] tokens = data.split("::=");
        String leftPart = tokens[0].trim();
        List<String> rightPart = List.of(tokens[1].trim().split(" \\| "));

        map.put(leftPart, rightPart);
    }

}
