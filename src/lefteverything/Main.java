package lefteverything;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

class Main {

    private static final FileReader fileReader = new FileReader();
    private static final LeftRecursionAnalyzer recursionAnalyzer = new LeftRecursionAnalyzer();
    private static final LeftFactorAnalyzer factorAnalyzer = new LeftFactorAnalyzer();
    private static final Printer printer = new Printer();

    public static void main(String... args) throws FileNotFoundException {

        Map<String, List<String>> rules = fileReader.readFile("resources\\input.txt");
        Map<String, List<String>> leftRecursiveRules = recursionAnalyzer.findLeftRecursion(rules);
        // TODO: fix this
        // Map<String, List<String>> fullRules = recursionAnalyzer.replaceEverythingEverywhere(rules);

        System.out.println("******RULES*****\n");
        printer.printResult(rules);
        System.out.println("******LEFT-RECURSION*****\n");
        printer.printResult(recursionAnalyzer.findLeftRecursion(rules));
        System.out.println("******LEFT-FACTORIZATION*****\n");
        printer.printResult(factorAnalyzer.eliminateLeftFactors(leftRecursiveRules));
    }
}
