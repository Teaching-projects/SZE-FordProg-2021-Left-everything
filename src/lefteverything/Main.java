package lefteverything;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

class Main {

    private static final FileReader fileReader = new FileReader();
    private static final LeftRecursionAnalyzer recursionAnalyzer = new LeftRecursionAnalyzer();

    public static void main(String... args) throws FileNotFoundException {
        Map<String, List<String>> rules = fileReader.readFile("resources\\input.txt");

        Map<String, List<String>> fullRules = recursionAnalyzer.replaceEverythingEverywhere(rules); //TODO: fix this
        Map<String, List<String>> leftRecursiveRules = recursionAnalyzer.findLeftRecursion(rules);
        for(String key: leftRecursiveRules.keySet()){
            System.out.println(key);
            leftRecursiveRules.get(key).forEach(v -> System.out.print(v + " "));
            System.out.println();

        }
    }

}
