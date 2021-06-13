package lefteverything;

import java.util.*;
import java.util.stream.Collectors;

public class LeftFactorAnalyzer {

    public Map<String, List<String>> eliminateLeftFactors(Map<String, List<String>> rules) {
        Map<String, List<String>> result = new HashMap<>();

        for (String rule : rules.keySet()) {
            List<String> prodRules = rules.get(rule).stream().sorted().collect(Collectors.toList());
            List<String> prefixes = findPrefixes(groupByFirstLetter(rules.get(rule))).stream().sorted().collect(Collectors.toList());
            Set<String> factoredRules = new HashSet<>();
            Map<String, List<String>> postFixesMap = new HashMap<>();

            if (prefixes.size() != 0) {
                for (String prefix : prefixes) {
                    List<String> postFixes = new ArrayList<>();
                    for (String prodRule : prodRules) {
                        if (prodRule.startsWith(prefix)) {
                            String nonTerminal = rule + "\'";
                            factoredRules.add(prodRule.replace(prodRule.substring(prefix.length()), nonTerminal));
                            postFixes.add(prodRule.substring(prefix.length()));
                            postFixesMap.put(prefix.substring(0, 1).toUpperCase() + "\'", postFixes);
                        } else {
                            factoredRules.add(prodRule);
                        }
                    }
                }
                result.put(rule, new ArrayList<>(factoredRules));
            } else {
                result.put(rule, prodRules);
            }

            result.putAll(postFixesMap);

            for (String resultKey : result.keySet()) {
                List<String> toBeRemoved = new ArrayList<>();
                for (String prefix : prefixes) {
                    for (String currentRule : result.get(resultKey)) {
                        if (currentRule.startsWith(prefix) && !currentRule.contains("\'")) {
                            toBeRemoved.add(currentRule);
                        }
                    }
                }
                result.get(resultKey).removeAll(toBeRemoved);
            }
        }
        return result;
    }

    private List<String> findPrefixes(Map<Character, List<String>> groupedStrings) {
        List<String> prefixes = new ArrayList<>();
        for (Character c : groupedStrings.keySet()) {
            List<String> wordList = groupedStrings.get(c);
            String[] wordArray = new String[wordList.size()];
            wordArray = wordList.toArray(wordArray);
            if (wordArray.length > 1) {
                prefixes.add(longestCommonPrefix(wordArray));
            }
        }
        return prefixes;
    }

    private Map<Character, List<String>> groupByFirstLetter(List<String> strings) {
        return strings.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
    }

    private String longestCommonPrefix(String[] a) {
        int size = a.length;
        if (size == 0)
            return "";
        if (size == 1)
            return a[0];
        Arrays.sort(a);

        int end = Math.min(a[0].length(), a[size - 1].length());

        int i = 0;
        while (i < end && a[0].charAt(i) == a[size - 1].charAt(i))
            i++;

        String pre = a[0].substring(0, i);
        return pre;
    }

}
